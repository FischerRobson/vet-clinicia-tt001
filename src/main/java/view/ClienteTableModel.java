/*
 *
 * @author Robson Fischer - 230599
 */
package view;
import java.util.List;
import model.Cliente;
import model.ClienteDAO;

public class ClienteTableModel extends GenericTableModel {
    
    public ClienteTableModel(List vDados){
        super(vDados, new String[]{"Nome", "Endereco", "CEP", "email", "Telefone"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch(columnIndex){
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");     
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Cliente client = (Cliente) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return client.getNome();
            case 1:
                return client.getEndereco();
            case 2:
                return client.getCep();
            case 3:
                return client.getEmail();
            case 4:
                return client.getTelefone();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        Cliente client = (Cliente) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                client.setNome((String)aValue);
                break;
            case 1:
                client.setEndereco((String)aValue);
                break;
            case 2:
                client.setCep((String)aValue);
                break;
            case 3:
                client.setEmail((String)aValue);
                break;
            case 4:
                client.setTelefone((String)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        ClienteDAO.getInstance().update(client);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }   
}
