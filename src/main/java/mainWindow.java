import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class mainWindow  extends JFrame {
    private JPanel body = new JPanel();
    private JList data = new JList();
    DisplayTable myt = new DisplayTable();
    JFrame me = this;

    public DisplayTable getTable(){
        return myt;
    }
    public mainWindow() {
        super("Simple Example");
        this.setSize(1200,700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnLoadPdf = new JButton("Завантажити PDF");
        btnLoadPdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PDF File","pdf");
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(body);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String dbp = "";
                    try{
                        dbp = checker.checkAndCreate();
                    } catch (Exception ex) {
                        JOptionPane optionPane = new JOptionPane(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                        JDialog dialog = optionPane.createDialog("Failure");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                    }
                    try {
                        MyParser prs = new MyParser(selectedFile.getPath());
                        //System.out.println(sqlRequestsForInterface.getTeachers().toArray());
                        //data = new JList(sqlRequestsForInterface.getTeachers().toArray());
                    } catch (Exception exc) {
                        System.out.println(exc.getMessage());
                        JOptionPane optionPane = new JOptionPane(exc.getMessage(), JOptionPane.ERROR_MESSAGE);
                        JDialog dialog = optionPane.createDialog("Failure");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                    }
                }
            }
        });

        JPanel sideBtnPanel = new JPanel();
        sideBtnPanel.setLayout(new BoxLayout(sideBtnPanel, BoxLayout.Y_AXIS));
        btnLoadPdf.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnExportExcel = new JButton("Експорт у Excel");
        btnExportExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    accessToExcel qExc = new accessToExcel();
                    qExc.exportFromResultSet(QueryPanel.tempRes);
                } catch (Exception exc) {
                    JOptionPane optionPane = new JOptionPane(exc.getMessage(), JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }
            }
        });
        btnExportExcel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnDeleteDB = new JButton("Видалити базу");
        btnDeleteDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteStatements.deleteDatabase();
                    dispatchEvent(new WindowEvent(me, WindowEvent.WINDOW_CLOSING));
                } catch (Exception exc) {
                    JOptionPane optionPane = new JOptionPane(exc.getMessage(), JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }
            }
        });
        btnDeleteDB.setAlignmentX(Component.CENTER_ALIGNMENT);
        sideBtnPanel.add(btnLoadPdf);
        sideBtnPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        sideBtnPanel.add(btnExportExcel);
        sideBtnPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        sideBtnPanel.add(btnDeleteDB);

        body.setLayout(new BoxLayout(body, BoxLayout.X_AXIS));

        JTable table = new JTable(myt);
        JScrollPane scr = new JScrollPane(table);
        body.add(scr);
        body.add(sideBtnPanel);

        QueryPanel head = new QueryPanel(this);
        JPanel queryHead = new JPanel();
        queryHead.add(head);
        Container container2 = this.getContentPane();
        container2.setLayout(new BoxLayout(container2, BoxLayout.Y_AXIS));
        container2.add(queryHead);
        container2.add(body);

        changeFont(container2, new Font(Font.SANS_SERIF, Font.PLAIN, 14));
    }
    public static void changeFont ( Component component, Font font )
    {
        component.setFont ( font );
        if ( component instanceof Container )
        {
            for ( Component child : ( ( Container ) component ).getComponents () )
            {
                changeFont ( child, font );
            }
        }
    }
    public static void main(String[] args) throws IOException, SQLException {
        checker.checkAndCreate();
        Database.connect();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            checker.checkAndCreate();
            Database.connect();
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException | IOException e) {
            e.printStackTrace();
        }
        mainWindow ww = new mainWindow();
        ww.setVisible(true);
    }
}