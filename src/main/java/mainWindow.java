import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

public class mainWindow  extends JFrame {
    private JPanel queryHead = new JPanel();
    private JPanel body = new JPanel();
    private JPanel sideBtnPanel = new JPanel();
    private JButton btnLoadPdf = new JButton("Завантажити PDF");
    private JButton btnExportExcel = new JButton("Експорт у Excel");
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
                        System.out.println("\n\n************\n\n************\n\n************\n\n************\n\n************\n\n************\n\n************\n\n************");
                        System.out.println(sqlRequestsForInterface.getTeachers().toArray());
                        data = new JList(sqlRequestsForInterface.getTeachers().toArray());
                    } catch (Exception exc) {
                        System.out.println("\n\n************\n\n************\n\n************\n\n************\n\n************\n\n************\n\n************\n\n************");
                        System.out.println(exc.getMessage());
                        System.out.println("\n\n************\n\n************\n\n************\n\n************\n\n************\n\n************\n\n************\n\n************");
                    }
                }
            }
        });

        sideBtnPanel.setLayout(new BoxLayout(sideBtnPanel, BoxLayout.Y_AXIS));
        btnLoadPdf.setAlignmentX(Component.CENTER_ALIGNMENT);
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
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }
        mainWindow ww = new mainWindow();
        ww.setVisible(true);
    }
}