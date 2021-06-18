import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class DisplayTable extends AbstractTableModel {
    Vector cache;
    int colCount;
    String[] headers;

    public DisplayTable() {
        cache = new Vector();
    }

    public String getColumnName(int i) {
        return headers[i];
    }

    public int getColumnCount() {
        return colCount;
    }

    public int getRowCount() {
        return cache.size();
    }

    public Object getValueAt(int row, int col) {
        return ((String[]) cache.elementAt(row))[col];
    }

    public void setTable(ResultSet rs) {
        cache = new Vector();
        try {
            ResultSetMetaData meta = rs.getMetaData();
            colCount = meta.getColumnCount();

            headers = new String[colCount];
            for (int h = 1; h <= colCount; h++) {
                headers[h - 1] = meta.getColumnName(h);
                System.out.println(headers[h-1]);
                if(headers[h - 1].equals("STUD_ID") || headers[h - 1].equals("stud_id"))
                    headers[h - 1] ="Студент#";
                if(headers[h - 1].equals("кількість_недопусків"))
                    headers[h - 1] ="К-сть недопусків";
                if(headers[h - 1].equals("ПІБ_студент") || headers[h - 1].equals("ПІБ_СТУДЕНТ"))
                    headers[h - 1] ="ПІБ студента";
                if(headers[h - 1].equals("last_name"))
                    headers[h - 1] ="Прізвище";
                if(headers[h - 1].equals("first_name"))
                    headers[h - 1] ="Ім'я";
                if(headers[h - 1].equals("father_name"))
                    headers[h - 1] ="По-батькові";
                if(headers[h - 1].equals("recordbook_no"))
                    headers[h - 1] ="Номер залікової";
                if(headers[h - 1].equals("year_student"))
                    headers[h - 1] ="Курс";
                if(headers[h - 1].equals("id_subject") || headers[h - 1].equals("ID_SUBJECT"))
                    headers[h - 1] ="Предмет#";
                if(headers[h - 1].equals("name_subject"))
                    headers[h - 1] ="Назва предмета";
                if(headers[h - 1].equals("id_group"))
                    headers[h - 1] ="Група#";
                if(headers[h - 1].equals("name_group"))
                    headers[h - 1] ="Назва групи";
                if(headers[h - 1].equals("year_study"))
                    headers[h - 1] ="Навчальний рік";
                if(headers[h - 1].equals("semester"))
                    headers[h - 1] ="Семестр";
                if(headers[h - 1].equals("edu_level"))
                    headers[h - 1] ="Освітній рівень";
                if(headers[h - 1].equals("faculty"))
                    headers[h - 1] ="Факультет";
                if(headers[h - 1].equals("id_teacher") || headers[h - 1].equals("ID_TEACHER"))
                    headers[h - 1] ="Викладач#";
                if(headers[h - 1].equals("ПІБ_викладач") || headers[h - 1].equals("ПІБ_ВИКЛАДАЧ"))
                    headers[h - 1] ="ПІБ викладача";
                if(headers[h - 1].equals("position"))
                    headers[h - 1] ="Посада";
                if(headers[h - 1].equals("science_degree"))
                    headers[h - 1] ="Науковий ступінь";
                if(headers[h - 1].equals("academ_status"))
                    headers[h - 1] ="Академ. статус";
                if(headers[h - 1].equals("id_bih"))
                    headers[h - 1] ="Бігунець#";
                if(headers[h - 1].equals("id_mark_bih"))
                    headers[h - 1] ="Оцінка бігунця#";
                if(headers[h - 1].equals("id_mark_vid") || headers[h - 1].equals("ID_MARK_VID"))
                    headers[h - 1] ="Оцінка відомості#";
                if(headers[h - 1].equals("mark_ekts"))
                    headers[h - 1] ="Оцінка ЄКТС";
                if(headers[h - 1].equals("id_data_exam"))
                    headers[h - 1] ="Відомість#";
                if(headers[h - 1].equals("num_present"))
                    headers[h - 1] ="К-сть присутніх";
                if(headers[h - 1].equals("num_absent"))
                    headers[h - 1] ="К-сть відсутніх";
                if(headers[h - 1].equals("num_not_allowed"))
                    headers[h - 1] ="К-сть недопущених";
                if(headers[h - 1].equals("type_control") || headers[h - 1].equals("control"))
                    headers[h - 1] ="Тип контролю";
                if(headers[h - 1].equals("date_exam") || headers[h - 1].equals("date_taken"))
                    headers[h - 1] ="Дата проведення";
                if(headers[h - 1].equals("ok_till"))
                    headers[h - 1] ="Дійсний до";
                if(headers[h - 1].equals("reason"))
                    headers[h - 1] ="Причина перенесення";
                if(headers[h - 1].equals("mark_sem"))
                    headers[h - 1] ="Оцінка семестр";
                if(headers[h - 1].equals("mark_exam"))
                    headers[h - 1] ="Оцінка перевірки";
                if(headers[h - 1].equals("mark_tog"))
                    headers[h - 1] ="Оцінка разом";
                if(headers[h - 1].equals("mark_nat"))
                    headers[h - 1] ="Національна шкала";
                if(headers[h - 1].equals("КІЛЬКІСТЬ_НЕДОПУСКІВ"))
                    headers[h - 1] ="К-сть недопусків";
            }
            while (rs.next()) {
                String[] record = new String[colCount];
                for (int i = 0; i < colCount; i++) {
                    record[i] = rs.getString(i + 1);
                }
                cache.addElement(record);
            }
            fireTableChanged(null);
        } catch (Exception e) {
            cache = new Vector();
            e.printStackTrace();
        }
    }
}
