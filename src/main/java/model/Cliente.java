package model;

/**
 *
 * @author Robson Fischer - 230599
 */
import java.util.ArrayList;
import java.util.List;


public class Cliente {
    private int id;
    private String nome;
    private String endereco;
    private String cep;
    private String email;
    private String telefone;
    
    //Armazenando os animais do cliente em uma lista:
    private List<Animal> animais;
    
    public Cliente(int id, String nome, String endereco, String cep, String email, String telefone){
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.email = email;
        this.telefone = telefone;
        this.animais = new ArrayList<Animal>();
    }
    
    //Getters:
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public String getEmail() {
        return email;
    }
    
    //Setters:
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public void setEmail(String email){
        if(!email.equals("")){
            this.email = email;
        }
    }
    
    public void addAnimal(Animal animal) {
        if (!animal.getNome().isBlank()) {
            animais.add(animal);
        }
    }
    
    public void addAnimais(List<Animal> allAnimais) {
        int i = 0; 
        for (i=0; i<allAnimais.size(); i++) {
           if (!allAnimais.get(i).getNome().isBlank()) {
               animais.add(allAnimais.get(i));
            }
        }
    }

    public List<Animal> getAnimais() {
        List<Animal> copia = new ArrayList<>(animais.size());
        return copia;
    }
    
    @Override
    public String toString() {
        String desc = "Cliente(ID: " + id + ", nome: " + nome +", endereco: " + endereco + ", fone: " + telefone + ", CEP: " + cep + ", email: " + email + ")";
        String strAnimais = animais.toString();
        return desc + "\n" + strAnimais;
    }
}
