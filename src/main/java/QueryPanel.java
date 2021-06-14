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
        JComboBox<String> bareTables = new JComboBox(new String[]{"Студенти", "Викладачі", "Предмети", "Групи"});
        tabGeneral.add(bareTables);
        JButton sendBtnGen = new JButton("Знайти");
        tabGeneral.add(sendBtnGen);
        queries.addTab(GENERALPANEL, tabGeneral);
        sendBtnGen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    checker.checkAndCreate();
                    Database.connect();
                    container.getTable().setTable(sqlRequestsForInterface.getTeachersRS());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        JPanel tabStudent = new JPanel();
        tabStudent.add(new JLabel("Прізвище викладача"));
        JTextField tStTeacherF = new JTextField("", 15);
        tabStudent.add(tStTeacherF);
        tabStudent.add(new JLabel("Рік навчання"));
        JTextField tStYearF = new JTextField("", 15);
        tabStudent.add(tStYearF);
        tabStudent.add(new JLabel("Предмет"));
        JTextField tStSubjectF = new JTextField("", 15);
        tabStudent.add(tStSubjectF);
        JButton sendBtnSt = new JButton("Знайти");
        tabStudent.add(sendBtnSt);
        queries.addTab(STUDENTPANEL, tabStudent);

        JPanel tabRestudent = new JPanel();
        tabRestudent.add(new JLabel("Прізвище викладача"));
        JTextField tReStTeacherF = new JTextField("", 15);
        tabRestudent.add(tReStTeacherF);
        tabRestudent.add(new JLabel("Рік навчання"));
        JTextField tReStYearF = new JTextField("", 15);
        tabRestudent.add(tReStYearF);
        tabRestudent.add(new JLabel("Предмет"));
        JTextField tReStSubjectF = new JTextField("", 15);
        tabRestudent.add(tReStSubjectF);
        JButton sendBtnReSt = new JButton("Знайти");
        tabRestudent.add(sendBtnReSt);
        queries.addTab(RESTUDENTPANEL, tabRestudent);

        JPanel tabVidomist = new JPanel();
        tabVidomist.add(new JLabel("Прізвище викладача"));
        JTextField tVidTeacherF = new JTextField("", 15);
        tabVidomist.add(tVidTeacherF);
        tabVidomist.add(new JLabel("Рік навчання"));
        JTextField tVidSubjectF = new JTextField("", 15);
        tabVidomist.add(tVidSubjectF);
        tabVidomist.add(new JLabel("Предмет"));
        JTextField tVidYearF = new JTextField("", 15);
        tabVidomist.add(tVidYearF);
        tabVidomist.add(new JLabel("Прізвище студента"));
        JTextField tVidStudentF = new JTextField("", 15);
        tabVidomist.add(tVidStudentF);
        JButton sendBtnVid = new JButton("Знайти");
        tabVidomist.add(sendBtnVid);
        queries.addTab(VIDOMISTPANEL, tabVidomist);

        JPanel tabNedop = new JPanel();
        tabNedop.add(new JLabel("Прізвище викладача"));
        JTextField tNedTeacherF = new JTextField("", 15);
        tabNedop.add(tNedTeacherF);
        tabNedop.add(new JLabel("Прізвище студента"));
        JTextField tNedStudentF = new JTextField("", 15);
        tabNedop.add(tNedStudentF);
        JButton sendBtnNed = new JButton("Знайти");
        tabNedop.add(sendBtnNed);
        queries.addTab(NEDOPPANEL, tabNedop);

        JPanel tabRating = new JPanel();
        tabRating.add(new JLabel("Прізвище викладача"));
        JTextField tRateTeacherF = new JTextField("", 15);
        tabRating.add(tRateTeacherF);
        tabRating.add(new JLabel("Рік навчання"));
        JTextField tRateYearF = new JTextField("", 15);
        tabRating.add(tRateYearF);
        tabRating.add(new JLabel("Предмет"));
        JTextField tRateSubjectF = new JTextField("", 15);
        tabRating.add(tRateSubjectF);
        tabRating.add(new JLabel("Прізвище студента"));
        JTextField tRateStudentF = new JTextField("", 15);
        tabRating.add(tRateStudentF);
        JButton sendBtnRate = new JButton("Знайти");
        tabRating.add(sendBtnRate);
        queries.addTab(RATINGPANEL, tabRating);

        JPanel tabDebt = new JPanel();
        tabDebt.add(new JLabel("Прізвище викладача"));
        JTextField tabDebtTeacherF = new JTextField("", 15);
        tabDebt.add(tabDebtTeacherF);
        tabDebt.add(new JLabel("Рік навчання"));
        JTextField tabDebtYearF = new JTextField("", 15);
        tabDebt.add(tabDebtYearF);
        tabDebt.add(new JLabel("Предмет"));
        JTextField tabDebtSubjectF = new JTextField("", 15);
        tabDebt.add(tabDebtSubjectF);
        tabDebt.add(new JLabel("Прізвище студента"));
        JTextField tabDebtStudentF = new JTextField("", 15);
        tabDebt.add(tabDebtStudentF);
        JButton sendBtnDebt = new JButton("Знайти");
        tabDebt.add(sendBtnDebt);
        queries.addTab(DEBTORSSPANEL, tabDebt);

        JPanel tabSpecial = new JPanel();
        JComboBox ourQueries = new JComboBox(new String[] {"Котя-Марія", "Катя", "Настя"});
        tabSpecial.add(ourQueries);
        JButton sendBtnSpec = new JButton("Знайти");
        tabSpecial.add(sendBtnSpec);
        queries.addTab(SPECIALSPANEL, tabSpecial);
        container.add(queries);

    }
    /*public void itemStateChanged(ItemEvent event)
    {
        CardLayout layout = (CardLayout)(cards.getLayout());
        layout.show(cards, (String)event.getItem());
    }*/

}
