import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlRequestsForInterface {
    public static ResultSet getTeachers() throws IOException, SQLException {
        String sql = "SELECT *\n" +
                "FROM teacher;";
        Statement statement = Database.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        return result;
    }
    //temporary func for testing purposes
//    public static ResultSet getTeachersRS() throws IOException, SQLException {
//        ArrayList<String> res = new ArrayList<String>();
//        String sql = "SELECT *\n" +
//                "FROM teacher;";
//        Statement statement = Database.connection.createStatement();
//        ResultSet result = statement.executeQuery(sql);
//        return result;
//    }
    public static ResultSet getGroups() throws IOException, SQLException {
        String sql = "SELECT *\n" +
                "FROM group_st;";
        Statement statement = Database.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        return result;
    }
    public static ResultSet getSubjects() throws IOException, SQLException {
        String sql = "SELECT *\n" +
                "FROM subject;";
        Statement statement = Database.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        return result;
    }
    public static ResultSet getStudents() throws IOException, SQLException {
        String sql = "SELECT *\n" +
                "FROM student;";
        Statement statement = Database.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        return result;
    }
    public static ResultSet getBihunets() throws IOException, SQLException {
        String sql = "SELECT *\n" +
                "FROM bihunets;";
        Statement statement = Database.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        return result;
    }
    public static ResultSet getDataExam() throws IOException, SQLException {
        String sql = "SELECT *\n" +
                "FROM data_exam;";
        Statement statement = Database.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        return result;
    }
    public static ResultSet getMarkBih() throws IOException, SQLException {
        String sql = "SELECT *\n" +
                "FROM mark_bih;";
        Statement statement = Database.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        return result;
    }
    public static ResultSet getMarkVid() throws IOException, SQLException {
        String sql = "SELECT *\n" +
                "FROM mark_vid;";
        Statement statement = Database.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        return result;
    }
//    public static ArrayList<String> getCourses() throws IOException, SQLException {
//        ArrayList<String> res = new ArrayList<String>();
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "SELECT *\n" +
//                "FROM group_st;";
//        Statement statement = connection.createStatement();
//        ResultSet result = statement.executeQuery(sql);
//        while (result.next()) {
//            int id_t = result.getInt("year_student");
//            res.add(id_t+"");
//        }
//        return res;
//    }

}
