package model;

import model.Tratamento;
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
public class TratamentoDAO extends DAO {
    private static TratamentoDAO instance;

    private TratamentoDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static TratamentoDAO getInstance() {
        return (instance==null?(instance = new TratamentoDAO()):instance);
    }

// CRUD    
    public Tratamento create(String nome, Calendar dataInicio, Calendar dataFim, int idAnimal, boolean terminou, int id) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO cliente (nome, dataInicio, dataFim, idAnimal, terminou) VALUES (?,?,?,?,?)");
            stmt.setString(1, nome);
            stmt.setDate(2,new Date(dataInicio.getTimeInMillis()));
            stmt.setDate(3,new Date(dataFim.getTimeInMillis()));
            stmt.setInt(4, idAnimal);
            stmt.setBoolean(5, terminou);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("tratamento","id"));
    }
    
    // Uma pequena gambiarra, depois explico...
    public boolean isLastEmpty(){
        Tratamento lastTratamento = this.retrieveById(lastId("tratamento","id"));
        if (lastTratamento != null) {
            return lastTratamento.getNome().isBlank();
        }
        return false;
    }

    private Tratamento buildObject(ResultSet rs) {
        Tratamento tratamento = null;
        Calendar dataInicio = Calendar.getInstance();
        Calendar dataFim = Calendar.getInstance();
        try {
            tratamento = new Tratamento(rs.getString("nome"), dataInicio, dataFim, rs.getInt("idAnimal"), rs.getBoolean("terminou"), rs.getInt("id"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamento;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List<Tratamento> tratamentos = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                tratamentos.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamentos;
    }
    
    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM tratamento");
    }
    
    // RetrieveLast
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM tratamento WHERE id = " + lastId("tratamento","id"));
    }

    // RetrieveById
    public Tratamento retrieveById(int id) {
        List<Tratamento> tratamentos = this.retrieve("SELECT * FROM tratamento WHERE id = " + id);
        return (tratamentos.isEmpty()?null:tratamentos.get(0));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM tratamento WHERE nome LIKE '%" + nome + "%'");
    }    
        
    // Updade
    public void update(Tratamento tratamento) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE tratamento SET nome=?, dataInicio=?, dataFim=?, idAnimal=?, terminou=? WHERE nome=?");
            stmt.setString(1, tratamento.getNome());
            stmt.setDate(2, new Date(tratamento.getDataInicio().getTimeInMillis()));
            stmt.setDate(3, new Date(tratamento.getDataFim().getTimeInMillis()));
            stmt.setInt(4, tratamento.getIdAnimal());
            stmt.setBoolean(5, tratamento.isTerminou());
            stmt.setInt(6, tratamento.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
        // Delete   
    public void delete(Tratamento tratamento) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM tratamento WHERE id = ?");
            stmt.setInt(1, tratamento.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}