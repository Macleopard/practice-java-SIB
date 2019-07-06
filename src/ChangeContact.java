import javax.swing.*;
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

    ChangeContact(final PhoneTableModel model, int selectedRow){
        // fill array with text fields for working with them
        ArrayList<JTextField> fields = new ArrayList<>();
        fields.add(nameField);
        fields.add(surnameField);
        fields.add(companyField);
        fields.add(positionField);
        fields.add(mobilePhoneField);
        fields.add(workingPhoneField);

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
