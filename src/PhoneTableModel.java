import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PhoneTableModel extends AbstractTableModel {

    private int columnCount = 6;
    private static ArrayList<String []> dataArrayList;
    PhoneTableModel(){
        dataArrayList = new ArrayList<>();
    }

    static void clearData(){
        dataArrayList.clear();
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    void addContact(String[] row){
        String [] rowTable;
        rowTable = row;
        dataArrayList.add(rowTable);
    }

    String[] getContact(int selectedRow){
        return dataArrayList.get(selectedRow);
    }
    void deleteContact(int selectedRow){
        dataArrayList.remove(selectedRow);
    }

    @Override
    public String getColumnName(int column) {
        return Database.getString(column);
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        String [] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    String[] getValuesAt(int row){
        return dataArrayList.get(row);
    }

    void setValuesAt(int row, String[] contact){
        System.arraycopy(contact, 0, dataArrayList.get(row), 0, 6);
    }
}
