/*
 *
 * @author Robson Fischer - 230599
 */
package view;
import java.util.List;
import model.Especie;
import model.EspecieDAO;


public class EspecieTableModel extends GenericTableModel {
    
    public EspecieTableModel(List vDados){
        super(vDados, new String[]{"Nome", "Id"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch(columnIndex){
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");     
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Especie especie = (Especie) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return especie.getNome();
            case 1:
                return especie.getId();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        Especie especie = (Especie) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                especie.setNome((String)aValue);
                break;
            case 1:
                especie.setId((int)aValue);;
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        EspecieDAO.getInstance().update(especie);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }   
}