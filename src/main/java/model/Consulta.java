package model;

/**
 *
 * @author Robson Fischer - 230599
 */

import java.util.Calendar;

public class Consulta {
    private Calendar data;
    private int hora;
    private String historico;
    private int idAnimal;
    private int idVet;
    private int idTratamento;
    private boolean terminou;
    private int id;
    
    public Consulta(Calendar data, int hora, String historico, int idAnimal, int idVet, int idTratamento, boolean terminou, int id) {
        this.data = data;
        this.hora = hora;
        this.historico = historico;
        this.idAnimal = idAnimal;
        this.idVet = idVet;
        this.idTratamento = idTratamento;
        this.terminou = terminou;
        this.id = id;
    }
    
    //Getters:
        public Calendar getData() {
        return data;
    }

    public int getHora() {
        return hora;
    }

    public String getHistorico() {
        return historico;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public int getIdVet() {
        return idVet;
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public boolean isTerminou() {
        return terminou;
    }

    public int getId() {
        return id;
    }
    
    //Setters:
    public void setData(Calendar data) {
        this.data = data;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public void setIdVet(int idVet) {
        this.idVet = idVet;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }

    public void setId(int id) {
        this.id = id;
    }   
}
    



