import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;


public class PhoneBook extends JFrame {
    private JPanel panelMain;
    private JButton changeContactButton;
    private JButton viewAllInfoButton;
    private JButton addContactButton;
    private JButton deleteContactButton;
    private JTable table1;
    private PhoneTableModel phoneTableModel = new PhoneTableModel();
    private static JFileChooser fileChooser = new JFileChooser();

    private void openContacts(){
        fileChooser.setDialogTitle("Specify a file to open");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "TXT Files", "txt", "dat");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == 1)
            return;
        File file = fileChooser.getSelectedFile();
        System.out.println(file.getName());
        try {
            String buf;
            FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
            DataInputStream in = new DataInputStream(fileInputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while ((buf = br.readLine())!=null){
                String[] contact = buf.split(" ");
                phoneTableModel.addContact(contact);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveContacts(){
        fileChooser.setDialogTitle("Specify a file to open");
        fileChooser.showSaveDialog(null);
        try {
            File file = fileChooser.getSelectedFile();
            System.out.println(file.getName());
            PrintWriter os = new PrintWriter(file);
            for (int row = 0; row < table1.getRowCount(); row++) {
                for (int col = 0; col < table1.getColumnCount(); col++) {
                    os.print(table1.getValueAt(row, col) +" ");
                }
                os.println();
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PhoneBook() {
        table1.setModel(phoneTableModel);
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table1.getSelectedRow() != -1) {
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
            if (table1.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Please, select a contact", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ChangeContact changeContact = new ChangeContact(phoneTableModel, table1.getSelectedRow());
                changeContact.setVisible(true);
            }
        });
        deleteContactButton.addActionListener(e -> {
            if (table1.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Please, select a contact", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                phoneTableModel.deleteContact(table1.getSelectedRow());
            }
        });
        viewAllInfoButton.addActionListener(e -> {
            if (table1.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Please, select a contact", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String[] contactInfo = phoneTableModel.getContact(table1.getSelectedRow());
                String res = "Name: " + contactInfo[0] + "\n" +
                            "Surname: " + contactInfo[1] + "\n" +
                            "Company: " + contactInfo[2] + "\n" +
                            "Position: " + contactInfo[3] + "\n" +
                            "Mobile phone: " + contactInfo[4] + "\n" +
                            "Working phone: " + contactInfo[5] + "\n";
                JOptionPane.showMessageDialog(null, res, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private static void createGUI() {
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
            PhoneTableModel.clearData();
        });
        fileMenu.add(newMenu);
        JMenuItem openItem = new JMenuItem("Open phone book");
        openItem.addActionListener(e -> new PhoneBook().openContacts());
        fileMenu.add(openItem);
        JMenuItem saveItem = new JMenuItem("Save phone book");
        saveItem.addActionListener(e -> new PhoneBook().saveContacts());
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        exitItem.addActionListener(e -> System.exit(0));
        JMenu aboutMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutMenu.add(aboutItem);
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Made by Andrey Zimnistkiy BT-11 VGTU", "Author", JOptionPane.INFORMATION_MESSAGE));
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
