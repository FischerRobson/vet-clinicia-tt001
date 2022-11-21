/*
 *
 * @author Robson Fischer - 230599
 */
package controller;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import view.GenericTableModel;
import model.*;
import java.util.List;
import java.util.Calendar;
import view.*;

public class Controller {
    private static Cliente clienteSelecionado = null;
    private static Animal animalSelecionado = null;
    private static Veterinario veterinarioSelecionado = null;
    private static JTextField clienteSelecionadoTextField = null;
    private static JTextField animalSelecionadoTextField = null;
    private static JTextField veterinarioSelecionadoTextField = null;
    
    public static void setTextFields(JTextField cliente, JTextField animal, JTextField veterinario){
        clienteSelecionadoTextField = cliente;
        animalSelecionadoTextField = animal;
        veterinarioSelecionadoTextField = veterinario;
    }
    
    public static void setTableModel(JTable table, GenericTableModel tableModel){
        table.setModel(tableModel);    
    }
    
    public static Cliente getClienteSelecionado(){
        return clienteSelecionado;
    }
    
    public static Animal getAnimalSelecionado(){
        return animalSelecionado;
    }
    
    public static Veterinario getVeterinarioSelecionado(){
        return veterinarioSelecionado;
    }
    
    public static void setSelected(Object selected){
        if (selected instanceof Cliente){
            clienteSelecionado = (Cliente)selected;
            clienteSelecionadoTextField.setText(clienteSelecionado.getNome());
        }else if(selected instanceof Animal){
            animalSelecionado = (Animal)selected;
            animalSelecionadoTextField.setText(animalSelecionado.getNome());
        }else if(selected instanceof Veterinario){
            veterinarioSelecionado = (Veterinario)selected;
            veterinarioSelecionadoTextField.setText(veterinarioSelecionado.getNome());
        }
    }
    
    //Recupera clientes:
    public static List<Object> getTodosClientes(){
        return ClienteDAO.getInstance().retrieveAll();
    }
    
   //Recupera consultas:
    public static List getTodasConsultas(){
        return ConsultaDAO.getInstance().retrieveAll();
    }
    
    public static void atualizaNomeParecido(JTable table, String nomeParecido){           
        if(table.getModel() instanceof ClienteTableModel){
            ((GenericTableModel)table.getModel()).addListOfItems(ClienteDAO.getInstance().retrieveBySimilarName(nomeParecido));
        }else if(table.getModel() instanceof VeterinarioTableModel){
            ((GenericTableModel)table.getModel()).addListOfItems(VeterinarioDAO.getInstance().retrieveBySimilarName(nomeParecido));
        }else if(table.getModel() instanceof AnimalTableModel){
            if(getClienteSelecionado() != null){
                ((GenericTableModel)table.getModel()).addListOfItems(AnimalDAO.getInstance().retrieveBySimilarName(nomeParecido));
            }
        }else if(table.getModel() instanceof EspecieTableModel){
            ((GenericTableModel)table.getModel()).addListOfItems(EspecieDAO.getInstance().retrieveBySimilarName(nomeParecido));
        }       
    }

    //botao mostra todos:
    public static void atualizaBotaoTodos(JTable table, JTextField textfield){
        if(table.getModel() instanceof ClienteTableModel){
            ((GenericTableModel)table.getModel()).addListOfItems(Controller.getTodosClientes());
        }else if(table.getModel() instanceof VeterinarioTableModel){
            ((GenericTableModel)table.getModel()).addListOfItems(VeterinarioDAO.getInstance().retrieveAll());
        }else if(table.getModel() instanceof AnimalTableModel){
            if(getClienteSelecionado() != null){
                ((GenericTableModel)table.getModel()).addListOfItems(AnimalDAO.getInstance().retrieveByIdCliente(getClienteSelecionado().getId()));
            }
        }else if(table.getModel() instanceof EspecieTableModel){
            ((GenericTableModel)table.getModel()).addListOfItems(EspecieDAO.getInstance().retrieveAll());
        }
        textfield.setText("");
    }
      
    //adicionando itens ao BD:
    public static Cliente adicionaCliente(){
        return ClienteDAO.getInstance().create("", "", "", "", "");
    }
    
    public static Veterinario adicionaVeterinario(){
        return VeterinarioDAO.getInstance().create("", "", "");
    }
    
    public static Animal adicionaAnimal(){
        return AnimalDAO.getInstance().create("", 0, 0, 1, getClienteSelecionado().getId());
    }
    
    public static Especie adicionaEspecie(){
        return EspecieDAO.getInstance().create("");
    }
    
    public static Consulta adicionaConsulta(){
        return ConsultaDAO.getInstance().create(Calendar.getInstance(), 8, "", animalSelecionado.getId(), veterinarioSelecionado.getId(), 0, false);
    }
    
    //adicionaItems(botao novo):
    public static boolean adicionaItems(JTable table){
        if(table.getModel() instanceof ClienteTableModel){
            ((GenericTableModel)table.getModel()).addItem(Controller.adicionaCliente());
        }else if(table.getModel() instanceof VeterinarioTableModel){
            ((GenericTableModel)table.getModel()).addItem(Controller.adicionaVeterinario());
        }else if(table.getModel() instanceof AnimalTableModel){
            if(getClienteSelecionado() != null) {
                //BUG - NAO INSERE O NOME DA ESPECIE - checar novo animal:   
                ((GenericTableModel)table.getModel()).addItem(Controller.adicionaAnimal());
            }
        }else if(table.getModel() instanceof EspecieTableModel){
            ((GenericTableModel)table.getModel()).addItem(Controller.adicionaEspecie());
        }else if(table.getModel() instanceof ConsultaTableModel){
            //BUG - NAO CRIA NOVA CONSULTA - checar nova consulta:
            if((clienteSelecionado!=null) && (animalSelecionado!=null) && (veterinarioSelecionado!=null)){
                ((GenericTableModel)table.getModel()).addItem(Controller.adicionaConsulta());
            }else{
                return false;
            }     
        }
        return true;
    }
    
    //excluindo itens do BD:
    public static void excluiCliente(Cliente cliente){
        List<Object> animais = AnimalDAO.getInstance().retrieveByIdCliente(cliente.getId());
        for(Object animal : animais){
            AnimalDAO.getInstance().delete((Animal)animal);
        }
        ClienteDAO.getInstance().delete(cliente);
    }
    
    public static void excluiAnimal(Animal animal) {
        AnimalDAO.getInstance().delete(animal);
    }
    
    public static void excluiVeterinario(Veterinario vet) {
        VeterinarioDAO.getInstance().delete(vet);
    }
    
    public static boolean excluiConsulta(JTable table) {
        if(table.getSelectedRow() >= 0){
            Object item = ((GenericTableModel) table.getModel()).getItem(table.getSelectedRow());
            ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
            ConsultaDAO.getInstance().delete((Consulta)item);
            return true;
        }
        return false;
    }
    
    //exclusao(botao excluir):
    public static void excluiItems(JTable table) {
        if(table.getSelectedRow() >= 0){
            Object item = ((GenericTableModel) table.getModel()).getItem(table.getSelectedRow());
            ((GenericTableModel) table.getModel()).removeItem(table.getSelectedRow());
            if(table.getModel() instanceof ClienteTableModel){
                //BUG - NAO EXCLUI CLIENTE(ELE APAGA DPS VOLTA) - checar excluir cliente.
                excluiCliente(getClienteSelecionado());
            }else if(table.getModel() instanceof AnimalTableModel){
                excluiAnimal(getAnimalSelecionado());
            }else if(table.getModel() instanceof VeterinarioTableModel){
                excluiVeterinario(getVeterinarioSelecionado());
            }else if(table.getModel() instanceof EspecieTableModel){
                //BUG - NÃO EXCLUI ESPECIE - checar excluir especie.
                EspecieDAO.getInstance().delete((Especie)item);
            }
        } 
    }
    
    //filtrar consultas:
    public static void filtraConsultas(JTable table, JToggleButton jTodas, JToggleButton jHoje, JToggleButton jVet) {
        //terminar outros filtros
        if(table.getModel() instanceof ConsultaTableModel){
            String where = "";
            if(!jTodas.isSelected()){
                where = "WHERE data >= date('now')";
            }
            
            String query = "SELECT * FROM cosulta" + where + "ORDER BY data, horario";
            ((GenericTableModel) table.getModel()).addListOfItems(ConsultaDAO.getInstance().retrieve(query));
        }
    }
}
