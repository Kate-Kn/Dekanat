import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class QueryPanel implements ItemListener
{
    private static JPanel cards;

    final   static String SUBJECTPANEL = "предмети";
    final   static String GROUPPANEL   = "групи";
    final   static String STUDENTPANEL   = "групи";
    final   static String TEACHERPANEL   = "викладачі";
    final   static String VIDOMISTPANEL = "відомості";
    final   static String MARKVIDPANEL = "оцінки відомості";
    final   static String BIHUNETSPANEL   = "бігунці";
    final   static String MARKBIHPANEL = "оцінки бігунця";

    public QueryPanel(Container container) {
        JComboBox<String> tab = new JComboBox(new String[]{SUBJECTPANEL, GROUPPANEL, STUDENTPANEL, TEACHERPANEL,
                VIDOMISTPANEL, MARKVIDPANEL, BIHUNETSPANEL, MARKBIHPANEL});
        tab.setEditable(false);
        tab.addItemListener(this);

        JPanel cbPanel = new JPanel();
        cbPanel.add(tab);

        JPanel cardSubject = new JPanel();
        JComboBox<String> parametersSubject = new JComboBox(new String[]{"номер", "назва", "освітній рівень", "факультет"});
        cardSubject.add(new JLabel("Шукаємо за"));
        cardSubject.add(parametersSubject);
        cardSubject.add(new JTextField("", 15));

        JPanel cardGroup = new JPanel();
        JComboBox<String> parametersGroup = new JComboBox(new String[]{"шифр", "назва", "навчальний рік", "семестр", "курс"});
        cardGroup.add(new JLabel("Шукаємо за"));
        cardGroup.add(parametersGroup);
        cardGroup.add(new JTextField("", 15));

        JPanel cardVidomist = new JPanel();
        JComboBox<String> parametersVidomist = new JComboBox(new String[]{"номер", "кількість присутніх", "кількість відсутніх", "кількість недопущених", "тип контролю", "дата складання"});
        cardVidomist.add(new JLabel("Шукаємо за"));
        cardVidomist.add(parametersVidomist);
        cardVidomist.add(new JTextField("", 15));

        JPanel cardMarkVid = new JPanel();
        JComboBox<String> parametersMarkVid = new JComboBox(new String[]{"номер", "оцінка за трим", "оцінка перевірки", "оцінка разом", "оцінка нац. шкала", "оцінка єктс"});
        cardMarkVid.add(new JLabel("Шукаємо за"));
        cardMarkVid.add(parametersMarkVid);
        cardMarkVid.add(new JTextField("", 15));

        JPanel cardTeacher = new JPanel();
        JComboBox<String> parametersTeacher = new JComboBox(new String[]{"шифр", "ім'я", "прізвище", "по-батькові", "науковий ступінь", "вчене звання", "посада"});
        cardTeacher.add(new JLabel("Шукаємо за"));
        cardTeacher.add(parametersTeacher);
        cardTeacher.add(new JTextField("", 15));

        JPanel cardStudent = new JPanel();
        JComboBox<String> parametersStudent = new JComboBox(new String[]{"шифр", "ім'я", "прізвище", "по-батькові", "залікова книга"});
        cardStudent.add(new JLabel("Шукаємо за"));
        cardStudent.add(parametersStudent);
        cardStudent.add(new JTextField("", 15));

        JPanel cardBihunets = new JPanel();
        JComboBox<String> parametersBihunets = new JComboBox(new String[]{"номер", "дата складання", "дійсний до", "причина перенесення", "тип контролю"});
        cardBihunets.add(new JLabel("Шукаємо за"));
        cardBihunets.add(parametersBihunets);
        cardBihunets.add(new JTextField("", 15));

        JPanel cardMarkBih = new JPanel();
        JComboBox<String> parametersMarkBih = new JComboBox(new String[]{"номер", "оцінка за трим", "оцінка перевірки", "оцінка разом", "оцінка нац. шкала", "оцінка єктс"});
        cardMarkBih.add(new JLabel("Шукаємо за"));
        cardMarkBih.add(parametersMarkBih);
        cardMarkBih.add(new JTextField("", 15));

        cards = new JPanel(new CardLayout());

        cards.add(cardSubject, SUBJECTPANEL);
        cards.add(cardGroup, GROUPPANEL);
        cards.add(cardVidomist, STUDENTPANEL);
        cards.add(cardMarkVid, TEACHERPANEL);
        cards.add(cardTeacher, VIDOMISTPANEL);
        cards.add(cardStudent, MARKVIDPANEL);
        cards.add(cardBihunets, BIHUNETSPANEL);
        cards.add(cardBihunets, MARKBIHPANEL);

        JButton sendBtn = new JButton("Знайти");
        container.add(cbPanel, BorderLayout.PAGE_START);
        container.add(cards, BorderLayout.CENTER);
        container.add(sendBtn, BorderLayout.PAGE_END);
    }
    public void itemStateChanged(ItemEvent event)
    {
        CardLayout layout = (CardLayout)(cards.getLayout());
        layout.show(cards, (String)event.getItem());
    }

}
