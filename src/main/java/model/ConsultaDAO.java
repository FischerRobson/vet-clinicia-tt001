package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;

/**
 *
 * @author Robson Fischer - 230599
 */
public class ConsultaDAO extends DAO {
    private static ConsultaDAO instance;

    private ConsultaDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static ConsultaDAO getInstance() {
        return (instance==null?(instance = new ConsultaDAO()):instance);
    }

// CRUD    
    public Consulta create(Calendar data, int hora, String historico, int idAnimal, int idVet, int idTratamento, boolean terminou) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO consulta (data, hora, historico, idAnimal, idVet, idTratamento, terminou) VALUES (?,?,?,?,?,?,?)");
            //inserindo com setDate:
            stmt.setDate(1,new Date(data.getTimeInMillis()));
            stmt.setInt(2, hora);
            stmt.setString(3, historico);
            stmt.setInt(4, idAnimal);
            stmt.setInt(5, idVet);
            stmt.setInt(6, idTratamento);
            stmt.setBoolean(7, terminou);  
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("consulta","id"));
    }
    
    // Uma pequena gambiarra, depois explico...
    public boolean isLastEmpty(){
        Consulta lastConsulta = this.retrieveById(lastId("consulta","id"));
        if (lastConsulta != null) {
            return lastConsulta.getHistorico().isBlank();
        }
        return false;
    }

    private Consulta buildObject(ResultSet rs) {
        Consulta consulta = null;
        try {
            Calendar dt = Calendar.getInstance();
            dt.setTime(rs.getDate("data"));
            consulta = new Consulta(dt, rs.getInt("hora"), rs.getString("historico"), rs.getInt("idAnimal"), rs.getInt("idVet"), rs.getInt("idTratamento"), rs.getBoolean("terminou"), rs.getInt("id"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consulta;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List<Consulta> consultas = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                consultas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consultas;
    }
    
    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM consulta ORDER BY data, horario");
    }
    
    // RetrieveLast
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM consulta WHERE id = " + lastId("consulta","id"));
    }

    // RetrieveById
    public Consulta retrieveById(int id) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE id = " + id);
        return (consultas.isEmpty()?null:consultas.get(0));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarHistoric(String historico) {
        return this.retrieve("SELECT * FROM consulta WHERE historico LIKE '%" + historico + "%'");
    }    
        
    // Updade
    public void update(Consulta consulta) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE consulta SET data=?, historico=?, idAnimal=?, idVet=?, terminou=? WHERE id=?");
            stmt.setDate(1, new Date(consulta.getData().getTimeInMillis()));
            stmt.setString(2, consulta.getHistorico());
            stmt.setInt(3, consulta.getIdAnimal());
            stmt.setInt(4, consulta.getIdVet());
            stmt.setBoolean(5, consulta.isTerminou());
            stmt.setInt(6, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
        // Delete   
    public void delete(Consulta consulta) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM consulta WHERE id = ?");
            stmt.setInt(1, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}
