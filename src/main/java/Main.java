/**
 *
 * @author Robson Fischer - 230599
 */
//Bibliotecas:
import model.Cliente;
import model.ClienteDAO;
import model.VeterinarioDAO;
import model.EspecieDAO;
import model.ClienteDAO;
import model.AnimalDAO;
import java.util.ArrayList;
import java.util.List;
import model.Animal;

public class Main {
    public static void main(String[] args) {
        
        //ClienteDAO.getInstance().create("Plinio","Rua dos Bobos","70707070","00234-900","prvilela@unicamp.br");
        //ClienteDAO.getInstance().create("Spiderman","New York City","921456789","00234-900","spider@avengers.com");
        //ClienteDAO.getInstance().create("Rocca","Cancun","400212121","13481-020","therocca@obrabo.com");
        
        //EspecieDAO.getInstance().create("Cachorro");
        //EspecieDAO.getInstance().create("Gato");
        //System.out.println(EspecieDAO.getInstance().retrieveAll());
        
        //VeterinarioDAO.getInstance().create("Jonas Rodriguez", "jrodriguez@oul.com", "8989230");
        
        //ClienteDAO.getInstance().delete(ClienteDAO.getInstance().retrieveById(5));
        //System.out.println(ClienteDAO.getInstance().retrieveAll());
        
        //AnimalDAO.getInstance().create("Toto", 5, 0, 1, 1);
        //AnimalDAO.getInstance().create("Junin", 7, 0, 1, 1);
        //AnimalDAO.getInstance().create("Fifi", 3, 1, 2, 1);
        //AnimalDAO.getInstance().create("Magrelin", 3, 0, 1, 2);
        //AnimalDAO.getInstance().create("Maiksson", 8, 0, 2, 2);
        //AnimalDAO.getInstance().create("Laranjito", 6, 0, 2, 3);
        
        //ClienteDAO clienteDAO = ClienteDAO.getInstance();
        //AnimalDAO animalDAO = AnimalDAO.getInstance();
        
        //Cliente clienteOne = clienteDAO.retrieveById(1);
        //Cliente clienteTwo = clienteDAO.retrieveById(2);
        //Cliente clienteThree = clienteDAO.retrieveById(3);
        //System.out.println(clienteOne);
        //System.out.println(clienteTwo);
        //System.out.println(clienteThree);
        
        //clienteOne.addAnimais(animalDAO.retrieveByIdCliente(clienteOne.getId()));
        //clienteTwo.addAnimais(animalDAO.retrieveByIdCliente(clienteTwo.getId()));
        //clienteThree.addAnimais(animalDAO.retrieveByIdCliente(clienteThree.getId()));

        //System.out.println(clienteOne.toString());
        //System.out.println(clienteThree.toString());
        
        //System.out.println(AnimalDAO.getInstance().retrieveAll());
        //System.out.println(AnimalDAO.getInstance().retrieveById(2));
        //System.out.println(AnimalDAO.getInstance().retrieveBySimilarName("Junin"));
        //EspecieDAO.getInstance().create("Gato");
        //VeterinarioDAO.getInstance().create("Jonas Rodriguez", "Av. Marco Polo 123", "8989230");
        //System.out.println(VeterinarioDAO.getInstance().retrieveAll());
        
        //System.out.println(EspecieDAO.getInstance().retrieveAll());
        //System.out.println(AnimalDAO.getInstance().retrieveAll());
    }
}