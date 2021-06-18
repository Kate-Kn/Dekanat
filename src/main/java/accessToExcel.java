import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class accessToExcel {
    public static void main(String[] args) throws IOException, SQLException {
        Database.connect();
        accessToExcel exporter = new accessToExcel();
        //exporter.export("student");
        exporter.exportFromResultSet(sqlRequestsForInterface.getStudents());
    }
    private String getFileName(String baseName) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateTimeInfo = dateFormat.format(new Date());
        return baseName.concat(String.format("_%s.xlsx", dateTimeInfo));
    }
    public void exportFromResultSet( ResultSet result) throws SQLException, IOException {
        String nameOfPath = "excel";
        String excelFilePath = getFileName(nameOfPath.concat("_Export"));
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(nameOfPath);
        writeHeaderLine(result, sheet);
        writeDataLines(result, workbook, sheet);
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        workbook.close();
    }
    public void exportFromResultSetWithPath( ResultSet result, String path, String sheetName) throws SQLException, IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
        writeHeaderLine(result, sheet);
        writeDataLines(result, workbook, sheet);
        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
        workbook.close();
    }
    public void export(String table) throws SQLException, IOException {
        String excelFilePath = getFileName(table.concat("_Export"));
        String sql = "SELECT *" +
                "FROM ".concat(table);
       Statement st = Database.connection.createStatement();
       ResultSet result = st.executeQuery(sql);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(table);
         writeHeaderLine(result, sheet);
         writeDataLines(result, workbook, sheet);
         FileOutputStream outputStream = new FileOutputStream(excelFilePath);
         workbook.write(outputStream);
         workbook.close();
        st.close();
    }
    private void writeHeaderLine(ResultSet result, XSSFSheet sheet) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
        Row headerRow = sheet.createRow(0);
        for (int i = 1; i <= numberOfColumns; i++) {
            String columnName = metaData.getColumnName(i);
            Cell headerCell = headerRow.createCell(i-1);
            headerCell.setCellValue(columnName);
        }
    }
    private void writeDataLines(ResultSet result, XSSFWorkbook workbook, XSSFSheet sheet)
            throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
        int rowCount = 1;
        while (result.next()) {
            Row row = sheet.createRow(rowCount++);
            for (int i = 1; i <= numberOfColumns; i++) {
                Object valueObject = result.getObject(i);
                Cell cell = row.createCell(i-1);
                try {
                    System.out.println(valueObject.getClass());
                }catch(Exception e)
                {}
                if (valueObject instanceof Boolean)
                    cell.setCellValue((Boolean) valueObject);
                else if (valueObject instanceof Double)
                    cell.setCellValue((Double)valueObject);
                else if (valueObject instanceof Float)
                    cell.setCellValue((Float)valueObject);
                else if (valueObject instanceof Date) {
                    cell.setCellValue((Date) valueObject);
                    formatDateCell(workbook, cell);
                }else if(valueObject instanceof Integer)
                    cell.setCellValue((Integer) valueObject);
                else if(valueObject instanceof Long)
                    cell.setCellValue((Long) valueObject);
                else
                    cell.setCellValue((String) valueObject);
            }
        }
    }
    private void formatDateCell(XSSFWorkbook workbook, Cell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        cell.setCellStyle(cellStyle);
    }
}