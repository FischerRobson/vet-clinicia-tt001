package model;

/**
 *
 * @author Robson Fischer - 230599
 */
public class Especie {
    private String nome;
    private int id;
    
    public Especie(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    //Getter:
    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
    
    //Setter:
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
