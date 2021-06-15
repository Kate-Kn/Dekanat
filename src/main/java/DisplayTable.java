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

            // Now we must rebuild the headers array with the new column names
            headers = new String[colCount];
            for (int h = 1; h <= colCount; h++) {
                headers[h - 1] = meta.getColumnName(h);
                System.out.println(headers[h-1]);
                if(headers[h - 1].equals("STUD_ID"))
                    headers[h - 1] ="ідентифікатор студента";
            }

            // and file the cache with the records from our query. This would
            // not be
            // practical if we were expecting a few million records in response
            // to our
            // query, but we aren't, so we can do this.
            while (rs.next()) {
                String[] record = new String[colCount];
                for (int i = 0; i < colCount; i++) {
                    record[i] = rs.getString(i + 1);
                }
                cache.addElement(record);
            }
            fireTableChanged(null); // notify everyone that we have a new table.
        } catch (Exception e) {
            cache = new Vector(); // blank it out and keep going.
            e.printStackTrace();
        }
    }
}
