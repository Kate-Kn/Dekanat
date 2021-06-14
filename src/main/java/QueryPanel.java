import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;

public class QueryPanel extends JPanel
{
    final   static String GENERALPANEL = "Усі дані";
    final   static String STUDENTPANEL   = "Студенти";
    final   static String RESTUDENTPANEL   = "Студенти-перескладанці";
    final   static String VIDOMISTPANEL = "Відомості";
    final   static String NEDOPPANEL = "Недопуски";
    final   static String RATINGPANEL = "Рейтинг";
    final   static String DEBTORSSPANEL   = "Боржники";
    final   static String SPECIALSPANEL   = "Спеціальні";

    public QueryPanel(mainWindow container) {

        JTabbedPane queries = new JTabbedPane();

        JButton sendBtn = new JButton("Знайти");

        JPanel tabGeneral = new JPanel();
        JComboBox<String> bareTables = new JComboBox(new String[]{"Студент", "Викладач", "Предмет", "Група"});
        tabGeneral.add(bareTables);
        JButton sendBtnGen = new JButton("Знайти");
        tabGeneral.add(sendBtnGen);
        queries.addTab(GENERALPANEL, tabGeneral);
        sendBtnGen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    container.getTable().setTable(sqlRequestsForInterface.getTeachersRS());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        JPanel tabStudent = new JPanel();
        tabStudent.add(new JLabel("surname"));
        JTextField tStTeacherF = new JTextField("", 15);
        tabStudent.add(tStTeacherF);
        tabStudent.add(new JLabel("year"));
        JTextField tStYearF = new JTextField("", 15);
        tabStudent.add(tStYearF);
        tabStudent.add(new JLabel("subj"));
        JTextField tStSubjectF = new JTextField("", 15);
        tabStudent.add(tStSubjectF);
        tabStudent.add(sendBtn);
        queries.addTab(STUDENTPANEL, tabStudent);

        JPanel tabRestudent = new JPanel();
        tabRestudent.add(new JLabel("surname"));
        JTextField tReStTeacherF = new JTextField("", 15);
        tabRestudent.add(tReStTeacherF);
        tabRestudent.add(new JLabel("year"));
        JTextField tReStYearF = new JTextField("", 15);
        tabRestudent.add(tReStYearF);
        tabRestudent.add(new JLabel("subj"));
        JTextField tReStSubjectF = new JTextField("", 15);
        tabRestudent.add(tReStSubjectF);
        tabRestudent.add(sendBtn);
        queries.addTab(RESTUDENTPANEL, tabRestudent);

        JPanel tabVidomist = new JPanel();
        tabVidomist.add(new JLabel("surname"));
        JTextField tVidTeacherF = new JTextField("", 15);
        tabVidomist.add(tVidTeacherF);
        tabVidomist.add(new JLabel("year"));
        JTextField tVidSubjectF = new JTextField("", 15);
        tabVidomist.add(tVidSubjectF);
        tabVidomist.add(new JLabel("subj"));
        JTextField tVidYearF = new JTextField("", 15);
        tabVidomist.add(tVidYearF);
        tabVidomist.add(new JLabel("st"));
        JTextField tVidStudentF = new JTextField("", 15);
        tabVidomist.add(tVidStudentF);
        tabVidomist.add(sendBtn);
        queries.addTab(VIDOMISTPANEL, tabVidomist);

        JPanel tabNedop = new JPanel();
        tabNedop.add(new JLabel("subj"));
        JTextField tNedTeacherF = new JTextField("", 15);
        tabNedop.add(tNedTeacherF);
        tabNedop.add(new JLabel("subj"));
        JTextField tNedStudentF = new JTextField("", 15);
        tabNedop.add(tNedStudentF);
        tabNedop.add(sendBtn);
        queries.addTab(NEDOPPANEL, tabNedop);

        /*JPanel tabRating = new JPanel();
        JLabel sign = new JLabel("surname");
        JTextField textField = new JTextField("", 15);
        JLabel sign = new JLabel("year");
        JTextField textField = new JTextField("", 15);
        JLabel sign = new JLabel("subj");
        JTextField textField = new JTextField("", 15);
        JLabel sign = new JLabel("st");
        JTextField textField = new JTextField("", 15);

        JPanel tabDebt = new JPanel();
        JLabel sign = new JLabel("surname");
        JTextField textField = new JTextField("", 15);
        JLabel sign = new JLabel("year");
        JTextField textField = new JTextField("", 15);
        JLabel sign = new JLabel("subj");
        JTextField textField = new JTextField("", 15);
        JLabel sign = new JLabel("st");
        JTextField textField = new JTextField("", 15);

        JPanel tabSpecial = new JPanel();*/
        container.add(queries);

    }
    /*public void itemStateChanged(ItemEvent event)
    {
        CardLayout layout = (CardLayout)(cards.getLayout());
        layout.show(cards, (String)event.getItem());
    }*/

}
