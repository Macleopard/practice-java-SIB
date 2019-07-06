import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChangeContact extends JFrame {

    private JPanel ChangeContactPanel;
    private JButton changeInfoButton;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField positionField;
    private JTextField mobilePhoneField;
    private JTextField workingPhoneField;
    private JTextField companyField;
    private JPanel jpanel;
    private ArrayList<JTextField> fields = new ArrayList<>();
    private boolean isDataEntered(ArrayList<JTextField> textFields1){
        boolean dataEntered = true;
        for (JTextField text: textFields1){
            if (text.getText().trim().length()==0)
                dataEntered = false;
        }
        return dataEntered;
    }
    private void checkData(){
        changeInfoButton.setEnabled(isDataEntered(fields));
    }

    private void setDocListener(ArrayList<JTextField> jTextFields, DocumentListener documentListener){
        for (JTextField jTextField: jTextFields)
            jTextField.getDocument().addDocumentListener(documentListener);
    }

    ChangeContact(final PhoneTableModel model, int selectedRow){
        // fill array with text fields for working with them
        fields.add(nameField);
        fields.add(surnameField);
        fields.add(companyField);
        fields.add(positionField);
        fields.add(mobilePhoneField);
        fields.add(workingPhoneField);
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkData();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                checkData();
            }
            @Override
            public void changedUpdate(DocumentEvent e) { }
        };
        // setting for all text fields current documentListener
        setDocListener(fields, documentListener);

        setContentPane(ChangeContactPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        // fill info into text fields as prompt
        String[] info = model.getValuesAt(selectedRow);
        for (int i = 0; i < 6; i++){
            fields.get(i).setText(info[i]);
        }
        changeInfoButton.addActionListener(e -> {
            String[] readyContact = new String[6];
            for (int i = 0; i < fields.size();i++)
                readyContact[i] = fields.get(i).getText().trim();
            model.setValuesAt(selectedRow, readyContact);
            JOptionPane.showMessageDialog(null,"Contact has been successfully changed", "Info", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
    }



}
