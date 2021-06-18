import org.codehaus.plexus.util.StringUtils;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

public class QueryPanel extends JPanel
{
    final   static String GENERALPANEL = "Усі дані";
    final   static String STUDENTPANEL   = "Успішні студенти";
    final   static String RESTUDENTPANEL   = "Студенти-перескладанці";
    final   static String VIDOMISTPANEL = "Відомості";
    final   static String NEDOPPANEL = "Недопуски";
    final   static String RATINGPANEL = "Рейтинг";
    final   static String DEBTORSSPANEL   = "Боржники";
    public ResultSet tempRes;

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
                            tempRes = sqlRequestsForInterface.getStudents();
                            container.getTable().setTable(tempRes);
                            break;
                        case "Викладачі":
                            tempRes = sqlRequestsForInterface.getTeachers();
                                    container.getTable().setTable(tempRes);
                            break;
                        case "Предмети":
                            tempRes = sqlRequestsForInterface.getSubjects();
                            container.getTable().setTable(tempRes);
                            break;
                        case "Групи":
                            tempRes = sqlRequestsForInterface.getGroups();;
                            container.getTable().setTable(tempRes);
                            break;
                        case "Відомості":
                            tempRes = sqlRequestsForInterface.getDataExam();
                            container.getTable().setTable(tempRes);
                            break;
                        case "Бігунці":
                            tempRes = sqlRequestsForInterface.getBihunets();
                            container.getTable().setTable(tempRes);
                            break;
                        case "Оцінки відомостей":
                            tempRes = sqlRequestsForInterface.getMarkVid();
                            container.getTable().setTable(tempRes);
                            break;
                        case "Оцінки бігунців":
                            tempRes = sqlRequestsForInterface.getMarkBih();
                            container.getTable().setTable(tempRes);
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
                    tempRes = sqlRequests.getStudentsByFieldsInput(tStSubjectF.getText(), tStTeacherF.getText(), toInt(tStYearF.getText()));
                    container.getTable().setTable(tempRes);
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
                    tempRes = sqlRequests.getRetakeForFieldsInput(tReStSubjectF.getText(), toInt(tReStYearF.getText()), tReStTeacherF.getText());
                    container.getTable().setTable(tempRes);
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
                    tempRes = sqlRequests.getVidomistByFieldsInput(tVidTeacherF.getText(), tVidSubjectF.getText(), toInt(tVidYearF.getText()), tVidStudentF.getText());
                    container.getTable().setTable(tempRes);
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
//        tabNedop.add(new JLabel("Прізвище студента"));
//        JTextField tNedStudF = new JTextField("", 15);
//        tabNedop.add(tNedStudF);
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
                    tempRes = sqlRequests.getNumOfNedInput(toInt(tNedYearF.getText()), tNedSubF.getText(), tNedTeaF.getText());
                    container.getTable().setTable(tempRes);
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
                    tempRes = sqlRequests.statisticsInput(tRateSubjectF.getText(), tRateTeacherF.getText(), tRateStudentF.getText(), toInt(tRateYearF.getText()));
                    container.getTable().setTable(tempRes);
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
                    tempRes = sqlRequests.getDebtorByFieldsInput(tabDebtStudentF.getText(), tabDebtSubjectF.getText(), toInt(tabDebtYearF.getText()), tabDebtTeacherF.getText());
                    container.getTable().setTable(tempRes);
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

