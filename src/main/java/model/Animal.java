package model;

/**
 *
 * @author Robson Fischer - 230599
 */

public class Animal {
    private int id;
    private String nome;
    private int idade;
    private int sexo;   // 0-Macho,  1-Femea
    private int idEspecie;
    private int idCliente;
    
    public Animal(int id, String nome, int idade, int sexo, int idEspecie, int idCliente){
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.idEspecie = idEspecie;
        this.idCliente = idCliente;
    }

    //Getters:
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getSexo() {
        return sexo;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public int getIdCliente() {
        return idCliente;
    }

     public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    @Override
    public String toString() {
        return "Animal(ID: " + id + ", nome: " + nome + ", idade: " + idade + ", sexo: " + sexo + ", Esp: " + idEspecie + ", Cliente: " + idCliente + ")\n";
    }
}
