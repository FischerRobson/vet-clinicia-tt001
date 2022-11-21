package model;

/**
 *
 * @author Robson Fischer - 230599
 */

import java.util.Calendar;

public class Tratamento {
    private String nome;
    private Calendar dataInicio;
    private Calendar dataFim;
    private int idAnimal;
    private boolean terminou;
    private int id;
    
    public Tratamento(String nome, Calendar dataInicio, Calendar dataFim, int idAnimal, boolean terminou, int id) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.idAnimal = idAnimal;
        this.terminou = terminou;
        this.id = id;
    }
    
    //Getters:
    public String getNome() {
        return nome;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public Calendar getDataFim() {
        return dataFim;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public boolean isTerminou() {
        return terminou;
    }

    public int getId() {
        return id;
    }
    
    //Setters:
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(Calendar dataFim) {
        this.dataFim = dataFim;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
