import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PhoneTableModel extends AbstractTableModel {

    private int columnCount = 6;
    private ArrayList<String []> dataArrayList;
    public PhoneTableModel(){
        dataArrayList = new ArrayList<>();
        for (int i = 0; i < dataArrayList.size();i++){
            dataArrayList.add(new String[getColumnCount()]);
        }
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    public void addContact(String []row){
        String [] rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArrayList.add(rowTable);
    }

    public String[] getContact(int selectedRow){
        return dataArrayList.get(selectedRow);
    }
    public void deleteContact(int selectedRow){
        dataArrayList.remove(selectedRow);
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Name";
            case 1: return "Surname";
            case 2: return "Company";
            case 3: return "Position";
            case 4: return "Mobile phone";
            case 5: return "Working phone";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String [] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }
}
