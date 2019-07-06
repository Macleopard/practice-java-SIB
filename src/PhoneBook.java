import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PhoneBook extends JFrame{
    private JPanel panelMain;
    private JButton changeContactButton;
    private JButton viewAllInfoButton;
    private JButton addContactButton;
    private JButton deleteContactButton;
    private JTable table1;
    private PhoneTableModel phoneTableModel = new PhoneTableModel();

    private PhoneBook() {
        table1.setModel(phoneTableModel);
        Database database = new Database("Andrew","Andrew","Mac","Mac","88005553535","896956535");
        phoneTableModel.addContact(database.toStr());
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table1.getSelectedRow()!=-1){
                    ChangeContact changeContact = new ChangeContact(phoneTableModel, table1.getSelectedRow());
                    changeContact.setVisible(true);
                }
            }
        });
        addContactButton.addActionListener(e -> {
            AddContact addContact = new AddContact(phoneTableModel);
            addContact.setVisible(true);
        });
        changeContactButton.addActionListener(e -> {
            if (table1.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null,"Please, select a contact","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                ChangeContact changeContact = new ChangeContact(phoneTableModel, table1.getSelectedRow());
                changeContact.setVisible(true);
            }
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
        frame.setPreferredSize(new Dimension(500, 200));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException |
                IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        // creating menu bar and fill it
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenu = new JMenuItem("New phone book");
        newMenu.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"");
        });
        fileMenu.add(newMenu);
        JMenuItem openItem = new JMenuItem("Open phone book");
        openItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"");
        });
        fileMenu.add(openItem);
        JMenuItem saveItem = new JMenuItem("Save phone book");
        saveItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"");
        });
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        exitItem.addActionListener(e -> System.exit(0));
        JMenu aboutMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutMenu.add(aboutItem);
        aboutItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"Made by Andrey Zimnistkiy BT-11 VGTU","Author", JOptionPane.INFORMATION_MESSAGE);
        });
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
