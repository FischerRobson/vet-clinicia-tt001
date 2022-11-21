package model;

import model.Exame;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author @author Robson Fischer - 230599
 */
public class ExameDAO extends DAO {
    private static ExameDAO instance;

    private ExameDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static ExameDAO getInstance() {
        return (instance==null?(instance = new ExameDAO()):instance);
    }

// CRUD    
    public Exame create(int idConsulta, String descricao) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO exame (idConsulta, descricao) VALUES (?,?)");
            stmt.setInt(1, idConsulta);
            stmt.setString(2, descricao);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ExameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("exame","id"));
    }
    
    // Uma pequena gambiarra, depois explico...
    public boolean isLastEmpty(){
        Exame lastExame = this.retrieveById(lastId("exame","id"));
        if (lastExame != null) {
            return lastExame.getDescricao().isBlank();
        }
        return false;
    }

    private Exame buildObject(ResultSet rs) {
        Exame exame = null;
        try {
            exame = new Exame(rs.getInt("id"), rs.getString("descricao"), rs.getInt("id"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return exame;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List<Exame> exames = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                exames.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return exames;
    }
    
    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM exame");
    }
    
    // RetrieveLast
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM exame WHERE id = " + lastId("exame","id"));
    }

    // RetrieveById
    public Exame retrieveById(int id) {
        List<Exame> exames = this.retrieve("SELECT * FROM exame WHERE id = " + id);
        return (exames.isEmpty()?null:exames.get(0));
    }
    
     // RetrieveByIdConsulta
    public Exame retrieveByIdConsulta(int idConsulta) {
        List<Exame> exames = this.retrieve("SELECT * FROM exame WHERE id = " + idConsulta);
        //pegar ultimo exame:
        return (exames.isEmpty()?null:exames.get(exames.size()-1));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarDescription(String descricao) {
        return this.retrieve("SELECT * FROM exame WHERE nome LIKE '%" + descricao + "%'");
    }    
        
    // Updade
    public void update(Exame exame) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE cliente SET idConsulta=?, descricao=? WHERE id=?");
            stmt.setInt(1, exame.getIdConsulta());
            stmt.setString(2, exame.getDescricao());
            stmt.setInt(3, exame.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
        // Delete   
    public void delete(Exame exame) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM exame WHERE idConsulta = ?");
            stmt.setInt(1, exame.getIdConsulta());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}