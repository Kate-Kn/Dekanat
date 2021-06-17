import org.codehaus.plexus.util.StringUtils;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class QueryPanel extends JPanel
{
    final   static String GENERALPANEL = "Усі дані";
    final   static String STUDENTPANEL   = "Студенти";
    final   static String RESTUDENTPANEL   = "Студенти-перескладанці";
    final   static String VIDOMISTPANEL = "Відомості";
    final   static String NEDOPPANEL = "Недопуски";
    final   static String RATINGPANEL = "Рейтинг";
    final   static String DEBTORSSPANEL   = "Боржники";

    public QueryPanel(mainWindow container) {

        JTabbedPane queries = new JTabbedPane();


        JPanel tabGeneral = new JPanel();
        JComboBox<String> bareTables = new JComboBox(new String[]{"Студенти", "Викладачі", "Предмети", "Групи", "Відомості", "Бігунці", "Оцінки відомостей", "Оцінки бігунців"});
        tabGeneral.add(bareTables);
        JButton sendBtnGen = new JButton("Знайти");
        tabGeneral.add(sendBtnGen);
        queries.addTab(GENERALPANEL, tabGeneral);
        sendBtnGen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    switch ((String)bareTables.getSelectedItem()) {
                        case "Студенти":
                            container.getTable().setTable(sqlRequestsForInterface.getStudents());
                            break;
                        case "Викладачі":
                            container.getTable().setTable(sqlRequestsForInterface.getTeachers());
                            break;
                        case "Предмети":
                            container.getTable().setTable(sqlRequestsForInterface.getSubjects());
                            break;
                        case "Групи":
                            container.getTable().setTable(sqlRequestsForInterface.getGroups());
                            break;
                        case "Відомості":
                            container.getTable().setTable(sqlRequestsForInterface.getDataExam());
                            break;
                        case "Бігунці":
                            container.getTable().setTable(sqlRequestsForInterface.getBihunets());
                            break;
                        case "Оцінки відомостей":
                            container.getTable().setTable(sqlRequestsForInterface.getMarkVid());
                            break;
                        case "Оцінки бугунців":
                            container.getTable().setTable(sqlRequestsForInterface.getMarkBih());
                            break;
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        JPanel tabStudent = new JPanel();
        tabStudent.add(new JLabel("Предмет"));
        JTextField tStSubjectF = new JTextField("", 15);
        tabStudent.add(tStSubjectF);
        tabStudent.add(new JLabel("Прізвище викладача"));
        JTextField tStTeacherF = new JTextField("", 15);
        tabStudent.add(tStTeacherF);
        tabStudent.add(new JLabel("Курс"));
        JTextField tStYearF = new JTextField("", 15);
        ((AbstractDocument) tStYearF.getDocument()).setDocumentFilter(new NumericAndLengthFilter(1));
        tabStudent.add(tStYearF);
        JButton sendBtnSt = new JButton("Знайти");
        tabStudent.add(sendBtnSt);
        queries.addTab(STUDENTPANEL, tabStudent);
        sendBtnSt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    container.getTable().setTable(sqlRequests.getStudentsByFieldsInput(tStSubjectF.getText(), tStTeacherF.getText(), toInt(tStYearF.getText())));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        JPanel tabRestudent = new JPanel();
        tabRestudent.add(new JLabel("Прізвище викладача"));
        JTextField tReStTeacherF = new JTextField("", 15);
        tabRestudent.add(tReStTeacherF);
        tabRestudent.add(new JLabel("Курс"));
        JTextField tReStYearF = new JTextField("", 15);
        ((AbstractDocument) tReStYearF.getDocument()).setDocumentFilter(new NumericAndLengthFilter(1));
        tabRestudent.add(tReStYearF);
        tabRestudent.add(new JLabel("Предмет"));
        JTextField tReStSubjectF = new JTextField("", 15);
        tabRestudent.add(tReStSubjectF);
        JButton sendBtnReSt = new JButton("Знайти");
        tabRestudent.add(sendBtnReSt);
        queries.addTab(RESTUDENTPANEL, tabRestudent);
        sendBtnReSt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    container.getTable().setTable(sqlRequests.getRetakeForFieldsInput(tReStSubjectF.getText(), toInt(tReStYearF.getText()), tReStTeacherF.getText()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        JPanel tabVidomist = new JPanel();
        tabVidomist.add(new JLabel("Прізвище викладача"));
        JTextField tVidTeacherF = new JTextField("", 15);
        tabVidomist.add(tVidTeacherF);
        tabVidomist.add(new JLabel("Курс"));
        JTextField tVidSubjectF = new JTextField("", 15);
        ((AbstractDocument) tVidSubjectF.getDocument()).setDocumentFilter(new NumericAndLengthFilter(1));
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
        sendBtnVid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    container.getTable().setTable(sqlRequests.getVidomistByFieldsInput(tVidTeacherF.getText(), tVidSubjectF.getText(), toInt(tVidYearF.getText()), tVidStudentF.getText()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        JPanel tabNedop = new JPanel();
        tabNedop.add(new JLabel("Курс"));
        JTextField tNedYearF = new JTextField("", 15);
        tabNedop.add(tNedYearF);
        tabNedop.add(new JLabel("Предмет"));
        JTextField tNedSubF = new JTextField("", 15);
        tabNedop.add(tNedSubF);
        tabNedop.add(new JLabel("Прізвище студента"));
        JTextField tNedStudF = new JTextField("", 15);
        tabNedop.add(tNedStudF);
        tabNedop.add(new JLabel("Прізвище викладача"));
        JTextField tNedTeaF = new JTextField("", 15);
        tabNedop.add(tNedTeaF);
        JButton sendBtnNed = new JButton("Знайти");
        tabNedop.add(sendBtnNed);
        queries.addTab(NEDOPPANEL, tabNedop);
        sendBtnNed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    container.getTable().setTable(sqlRequests.getNumOfNedInput(toInt(tNedYearF.getText()), tNedSubF.getText(), tNedStudF.getText(), tNedTeaF.getText()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        JPanel tabRating = new JPanel();
        tabRating.add(new JLabel("Прізвище викладача"));
        JTextField tRateTeacherF = new JTextField("", 15);
        tabRating.add(tRateTeacherF);
        tabRating.add(new JLabel("Курс"));
        JTextField tRateYearF = new JTextField("", 15);
        ((AbstractDocument) tRateYearF.getDocument()).setDocumentFilter(new NumericAndLengthFilter(1));
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
        sendBtnRate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    container.getTable().setTable(sqlRequests.statisticsInput(tRateSubjectF.getText(), tRateTeacherF.getText(), tRateStudentF.getText(), toInt(tRateYearF.getText())));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        JPanel tabDebt = new JPanel();
        tabDebt.add(new JLabel("Прізвище викладача"));
        JTextField tabDebtTeacherF = new JTextField("", 15);
        tabDebt.add(tabDebtTeacherF);
        tabDebt.add(new JLabel("Курс"));
        JTextField tabDebtYearF = new JTextField("", 15);
        ((AbstractDocument) tabDebtYearF.getDocument()).setDocumentFilter(new NumericAndLengthFilter(1));
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
        sendBtnDebt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    container.getTable().setTable(sqlRequests.getDebtorByFieldsInput(tabDebtStudentF.getText(), tabDebtSubjectF.getText(), toInt(tabDebtYearF.getText()), tabDebtTeacherF.getText()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        container.add(queries);
    }
    private int toInt(String value) {
        return StringUtils.isNotBlank(value) ? Integer.parseInt(value) : 0;
    }
    private static class NumericAndLengthFilter extends DocumentFilter {
        private int length = 0;
        public NumericAndLengthFilter(int length) {
            this.length = length;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (isNumeric(string)) {
                if (this.length > 0 && fb.getDocument().getLength() + string.length() > this.length) {
                    return;
                }
                super.insertString(fb, offset, string, attr);
            }
        }
        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws  BadLocationException {
            if (isNumeric(text)) {
                if (this.length > 0 && fb.getDocument().getLength() + text.length() > this.length) {
                    return;
                }
                super.insertString(fb, offset, text, attrs);
            }
        }
        private boolean isNumeric(String text) {
            if (text == null || text.trim().equals("")) {
                return false;
            }
            for (int iCount = 0; iCount < text.length(); iCount++) {
                if (!Character.isDigit(text.charAt(iCount))) {
                    return false;
                }
            }
            return true;
        }
    }
}

