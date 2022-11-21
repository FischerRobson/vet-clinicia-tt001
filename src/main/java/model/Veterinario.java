package model;

/**
 *
 * @author Robson Fischer - 230599
 */

public class Veterinario {
    private String nome;
    private String email;
    private String telefone;
    private int id;
    
    public Veterinario(String nome, String email, String telefone, int id) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.id = id;
    }
    
    //Getters:
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getId() {
        return id;
    }
    
    //Setters:
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setId(int id) {
        this.id = id;
    }   
}
