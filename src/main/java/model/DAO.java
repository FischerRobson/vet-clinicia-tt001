package model;


// DAO Implementation for SQLite.
/**
 * @author Prof. Dr. Plínio Vilela - prvilela@unicamp.br
 * @date 16 de Agosto de 2021
 */
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO {
    public static final String DBURL = "jdbc:sqlite:vet2021.db";
    private static Connection con;
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Connect to SQLite
    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(DBURL);
                if (con != null) {
                    DatabaseMetaData meta = con.getMetaData();
                }
            } catch (SQLException e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return con;
    }

    protected ResultSet getResultSet(String query) {
        Statement s;
        ResultSet rs = null;
        try {
            s = (Statement) con.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return rs;
    }

    protected int executeUpdate(PreparedStatement queryStatement) throws SQLException {
        int update;
        update = queryStatement.executeUpdate();
        return update;
    }

    protected int lastId(String tableName, String primaryKey) {
        Statement s;
        int lastId = -1;
        try {
            s = (Statement) con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(" + primaryKey + ") AS id FROM " + tableName);
            if (rs.next()) {
                lastId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return lastId;
    }

    public static void terminar() {
        try {
            (DAO.getConnection()).close();
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Create table SQLite
    protected final boolean createTable() {
        try {
            PreparedStatement stmt;
            // Table client:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS cliente( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "end VARCHAR, \n"
                    + "cep VARCHAR, \n"
                    + "email VARCHAR, \n"
                    + "telefone VARCHAR); \n");
            executeUpdate(stmt);
            // Table animal:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS animal( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "idade INTEGER, \n"
                    + "sexo VARCHAR, \n"
                    + "id_especie INTEGER, \n"
                    + "id_cliente INTEGER); \n");
            executeUpdate(stmt);
            // Table species:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS especie( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR); \n");
            executeUpdate(stmt);
            // Table vet:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS veterinario( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "email VARCHAR, \n"
                    + "telefone VARCHAR); \n");
            executeUpdate(stmt);        
            // Table treatment:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS tratamento( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "id_animal INTEGER, \n"
                    + "nome VARCHAR, \n"
                    + "dataIni TEXT, \n"
                    + "dataFim TEXT, \n"
                    + "terminado INTEGER); \n");
            executeUpdate(stmt);
            // Table appointment:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS consulta( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "data DATE, \n"
                    + "horario VARCHAR, \n"
                    + "comentario VARCHAR, \n"
                    + "id_animal INTEGER, \n"
                    + "id_vet INTEGER, \n"
                    + "id_tratamento INTEGER, \n"
                    + "terminado INTEGER); \n");
            executeUpdate(stmt);            
             // Table exam:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS exame( \n"
                    + "id INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "id_consulta INTEGER); \n");
            executeUpdate(stmt);      
            // Default element for species:
            stmt = DAO.getConnection().prepareStatement("INSERT OR IGNORE INTO especie (id, nome) VALUES (1, 'Cachorro')");
            executeUpdate(stmt);
            stmt = DAO.getConnection().prepareStatement("INSERT OR IGNORE INTO especie (id, nome) VALUES (2, 'Gato')");
            executeUpdate(stmt);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
