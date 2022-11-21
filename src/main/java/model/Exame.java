package model;

/**
 *
 * @author Robson Fischer - 230599
 */
public class Exame {
    private int idConsulta;
    private String descricao;
    private int id;
    
    public Exame(int idConsulta, String descricao, int id) {
        this.idConsulta = idConsulta;
        this.descricao = descricao;
        this.id = id;
    }
    
    //Getter: 
    public int getIdConsulta() {
        return idConsulta;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }
    
    //Setter:
    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(int id) {
        this.id = id;
    }
}
