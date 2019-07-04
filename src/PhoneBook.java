import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class PhoneBook {
    private JPanel panelMain;
    private JButton changeContactButton;
    private JButton viewAllInfoButton;
    private JButton addContactButton;
    private JButton deleteContactButton;
    private JTable table1;

    public PhoneBook() {
        PhoneTableModel phoneTableModel =new PhoneTableModel();
        table1.setModel(phoneTableModel);
    }

    public static void createGUI(){
        JFrame frame = new JFrame("PhoneBook");
//      
        frame.setPreferredSize(new Dimension(450, 200));
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
        javax.swing.SwingUtilities.invokeLater(() -> createGUI());
    }

}
