import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class mainWindow  extends JFrame {
    private JPanel queryHead = new JPanel();
    private JPanel body = new JPanel();
    private JPanel sideBtnPanel = new JPanel();
    private JButton btnLoadPdf = new JButton("Load PDF");
    private JButton btnExportExcel = new JButton("Export to Excel");
    private JTable data = new JTable();
    private JScrollPane scr = new JScrollPane();

    public mainWindow() {
        super("Simple Example");
        this.setBounds(100,100,1200,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnLoadPdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(body);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null,
                            "Hi eartherners, your elected file: " + selectedFile.getAbsolutePath(),
                            "Output",
                            JOptionPane.PLAIN_MESSAGE);
                }

            }
        });
        body.setBackground(new Color(0x8080EF));
        queryHead.setBackground(new Color(0xEF9F80));

        sideBtnPanel.setLayout(new BoxLayout(sideBtnPanel, BoxLayout.Y_AXIS));
        btnLoadPdf.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExportExcel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sideBtnPanel.add(btnLoadPdf);
        sideBtnPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        sideBtnPanel.add(btnExportExcel);

        body.setLayout(new BoxLayout(body, BoxLayout.X_AXIS));
        scr.setViewportView(data);
        body.add(scr);
        body.add(sideBtnPanel);

        Container container2 = this.getContentPane();
        container2.setLayout(new BoxLayout(container2, BoxLayout.Y_AXIS));
        container2.add(queryHead);
        container2.add(body);


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