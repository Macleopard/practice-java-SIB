import jdk.nashorn.internal.scripts.JO;
import oracle.jrockit.jfr.JFR;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddContact  extends JFrame{
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField companyField;
    private JTextField positionField;
    private JTextField workingPhoneField;
    private JTextField mobilePhoneField;
    private JButton saveButton;
    private JPanel addContactPanel;
    private ArrayList<JTextField> textFields  = new ArrayList<>();
    private ArrayList<String> results = new ArrayList<>();


    private boolean isDataEntered(ArrayList<JTextField> textFields1){
        boolean dataEntered = true;
        for (JTextField text: textFields1){
            if (text.getText().trim().length()==0)
                dataEntered = false;
        }
        return dataEntered;
    }
    private void checkData(){
        saveButton.setEnabled(isDataEntered(textFields));
    }

    private void setDocListener(ArrayList<JTextField> jTextFields, DocumentListener documentListener){
        for (JTextField jTextField: jTextFields)
            jTextField.getDocument().addDocumentListener(documentListener);
    }


    public AddContact(final PhoneBook parent){
        // control input fields
        // pushing JTextField to ArrayList
        textFields.add(nameField);
        textFields.add(surnameField);
        textFields.add(companyField);
        textFields.add(positionField);
        textFields.add(mobilePhoneField);
        textFields.add(workingPhoneField);
        // Creating a simple document listener for our text fields
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
        setDocListener(textFields, documentListener);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(addContactPanel);
        pack();
        saveButton.addActionListener(e -> {
            for (JTextField jTextField: textFields){
                results.add(jTextField.getText());
            }
            String[] readyContact = results.toArray(new String[0]);
            parent.newContact(readyContact);
            setVisible(false);
        });
    }}


