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
                if(headers[h - 1].equals("STUD_ID"))
                    headers[h - 1] ="ідентифікатор студента";
                /*if(headers[h - 1].equals("STUD_ID"))
                    headers[h - 1] ="ідентифікатор студента";
                if(headers[h - 1].equals("STUD_ID"))
                    headers[h - 1] ="ідентифікатор студента";
                if(headers[h - 1].equals("STUD_ID"))
                    headers[h - 1] ="ідентифікатор студента";
                if(headers[h - 1].equals("STUD_ID"))
                    headers[h - 1] ="ідентифікатор студента";
                if(headers[h - 1].equals("STUD_ID"))
                    headers[h - 1] ="ідентифікатор студента";
                if(headers[h - 1].equals("STUD_ID"))
                    headers[h - 1] ="ідентифікатор студента";*/
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
