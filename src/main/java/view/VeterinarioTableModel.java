/*
 *
 * @author Robson Fischer - 230599
 */
package view;
import java.util.List;
import model.Veterinario;
import model.VeterinarioDAO;


public class VeterinarioTableModel extends GenericTableModel {
    
    public VeterinarioTableModel(List vDados){
        super(vDados, new String[]{"Nome", "Email", "Telefone", "Id"});
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
                return Integer.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");     
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Veterinario vet = (Veterinario) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return vet.getNome();
            case 1:
                return vet.getEmail();
            case 2:
                return vet.getTelefone();
            case 3:
                return vet.getId();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        Veterinario vet = (Veterinario) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                vet.setNome((String)aValue);
                break;
            case 1:
                vet.setEmail((String)aValue);;
                break;
            case 2:
                vet.setTelefone((String)aValue);
                break;
            case 3:
                vet.setId((int)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        VeterinarioDAO.getInstance().update(vet);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }   
}