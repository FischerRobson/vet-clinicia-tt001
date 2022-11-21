/*
 *
 * @author Robson Fischer - 230599
 */
package view;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Animal;
import model.AnimalDAO;
import model.ClienteDAO;
import model.Consulta;
import model.ConsultaDAO;
import model.VeterinarioDAO;
import model.Exame;
import model.ExameDAO;

public class ConsultaTableModel extends GenericTableModel {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
     public ConsultaTableModel(List vDados){
        super(vDados, new String[]{"Data", "Hora", "Cliente", "Animal", "Veterinario", "Historico", "Exame", "Fim"});
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
            case 5:
                return String.class;
            case 6:
                return String.class;
            case 7:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");     
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Consulta appointment = (Consulta) vDados.get(rowIndex);
        Animal animal;
        
        switch(columnIndex){
            case 0:
                return dateFormat.format(appointment.getData().getTime());
            case 1:
                return appointment.getHora();
            case 2:
                animal = AnimalDAO.getInstance().retrieveById(appointment.getIdAnimal());
                return ClienteDAO.getInstance().retrieveById(animal.getIdCliente()).getNome();
            case 3:
                animal = AnimalDAO.getInstance().retrieveById(appointment.getIdAnimal());
                return animal.getNome();
            case 4:
                return VeterinarioDAO.getInstance().retrieveById(appointment.getIdVet()).getNome();
            case 5:
                return appointment.getHistorico();
            case 6:
                Exame exame = ExameDAO.getInstance().retrieveByIdConsulta(appointment.getId());
                if(exame != null){
                    return exame.getDescricao();
                }
            case 7:
                return appointment.isTerminou();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        Consulta appointment = (Consulta) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                Calendar cal = Calendar.getInstance();
                try{
                    cal.setTime(dateFormat.parse((String)aValue));
                } catch(ParseException ex) {
                    Logger.getLogger(ConsultaTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                appointment.setData(cal);
                break;
            case 1:
                appointment.setHora((Integer)aValue);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                appointment.setHistorico((String)aValue);
                break;
            case 6:
                ExameDAO.getInstance().create(appointment.getId(), (String)aValue);
                break;
            case 7:
                appointment.setTerminou((Boolean)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        ConsultaDAO.getInstance().update(appointment);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        if((columnIndex<2) || (columnIndex>4)){
            return true;
        }else{
            return false;
        }
    }   
}
