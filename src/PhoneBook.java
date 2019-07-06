import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class PhoneBook extends JFrame{
    private JPanel panelMain;
    private JButton changeContactButton;
    private JButton viewAllInfoButton;
    private JButton addContactButton;
    private JButton deleteContactButton;
    private JTable table1;
    private PhoneTableModel phoneTableModel = new PhoneTableModel();

    void newContact(String[] contact){
        phoneTableModel.addContact(contact);
    }

    private PhoneBook() {
        table1.setModel(phoneTableModel);
        Database database = new Database("Andrew","Andrew","Mac","Mac","88005553535","896956535");
        phoneTableModel.addContact(database.toStr());
        addContactButton.addActionListener(e -> {
            AddContact addContact = new AddContact(this);
            addContact.setVisible(true);
        });
        changeContactButton.addActionListener(e -> {
                ChangeContact changeContact = new ChangeContact();
                changeContact.setVisible(true);
        });
        deleteContactButton.addActionListener(e -> {
            if (table1.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null,"Please, select a contact","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                phoneTableModel.deleteContact(table1.getSelectedRow());
            }
        });
        viewAllInfoButton.addActionListener(e -> {
            if (table1.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null,"Please, select a contact","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                String[] contactInfo = phoneTableModel.getContact(table1.getSelectedRow());
                String res = "Name: " + contactInfo[0] + "\n" +
                            "Surname: " + contactInfo[1] + "\n" +
                            "Company: " + contactInfo[2] + "\n" +
                            "Position: " + contactInfo[3] + "\n" +
                            "Mobile phone: " + contactInfo[4] + "\n"+
                            "Working phone: " + contactInfo[5] + "\n";
                JOptionPane.showMessageDialog(null,res,"Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private static void createGUI(){
        JFrame frame = new JFrame("PhoneBook");
        frame.setPreferredSize(new Dimension(450, 200));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException |
                IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
//
        // creating menu bar and fill it
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenu = new JMenuItem("New phone book");
        fileMenu.add(newMenu);
        JMenuItem openItem = new JMenuItem("Open phone book");
        fileMenu.add(openItem);
        JMenuItem saveItem = new JMenuItem("Save phone book");
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        exitItem.addActionListener(e -> System.exit(0));

        JMenu aboutMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutMenu.add(aboutItem);
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        frame.setContentPane(new PhoneBook().panelMain);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(PhoneBook::createGUI);
    }



}
