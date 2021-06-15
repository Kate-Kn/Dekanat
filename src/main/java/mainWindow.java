import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class mainWindow  extends JFrame {
    private JPanel body = new JPanel();
    private JList data = new JList();
    DisplayTable myt = new DisplayTable();

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
                        //...
                    }
                    JOptionPane.showMessageDialog(null,
                            "Hi eartherners, your elected file: " + selectedFile.getAbsolutePath(),
                            "Output",
                            JOptionPane.PLAIN_MESSAGE);
                    try {
                        MyParser prs = new MyParser(selectedFile.getPath());
                        //System.out.println(sqlRequestsForInterface.getTeachers().toArray());
                        //data = new JList(sqlRequestsForInterface.getTeachers().toArray());
                    } catch (Exception exc) {
                        System.out.println(exc.getMessage());
                    }
                }
            }
        });

        JPanel sideBtnPanel = new JPanel();
        sideBtnPanel.setLayout(new BoxLayout(sideBtnPanel, BoxLayout.Y_AXIS));
        btnLoadPdf.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnExportExcel = new JButton("Експорт у Excel");
        btnExportExcel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sideBtnPanel.add(btnLoadPdf);
        sideBtnPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        sideBtnPanel.add(btnExportExcel);

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
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        mainWindow ww = new mainWindow();
        ww.setVisible(true);
    }
}