import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class sqlRequestsForInterface {
    public static ArrayList<String> getTeachers() throws IOException, SQLException {
        ArrayList<String> res = new ArrayList<String>();
        String sql = "SELECT id_teacher, first_name+' '+last_name AS name_surname\n" +
                "FROM teacher;";
        Statement statement = Database.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id_t = result.getInt("id_teacher");
                String toAdd = result.getString("name_surname");
                res.add(id_t+"-"+toAdd);
            }
            return res;
    }
    //temporary func for testing purposes
    public static ResultSet getTeachersRS() throws IOException, SQLException {
        ArrayList<String> res = new ArrayList<String>();
        String sql = "SELECT id_teacher, first_name+' '+last_name AS name_surname\n" +
                "FROM teacher;";
        Statement statement = Database.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        return result;
    }
    public static ArrayList<String> getCourses() throws IOException, SQLException {
        ArrayList<String> res = new ArrayList<String>();
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT year_student\n" +
                "FROM group_st;";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id_t = result.getInt("year_student");
            res.add(id_t+"");
        }
        return res;
    }
    public static ArrayList<String> getGroups() throws IOException, SQLException {
        ArrayList<String> res = new ArrayList<String>();
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT id_group, name_group\n" +
                "FROM group_st;";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id_t = result.getInt("id_group");
            String toAdd = result.getString("name_group");
            res.add(id_t+"-"+toAdd);
        }
        return res;
    }
    public static ArrayList<String> getSubjects() throws IOException, SQLException {
        ArrayList<String> res = new ArrayList<String>();
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT id_subject, name_subject\n" +
                "FROM subject;";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id_t = result.getInt("id_subject");
            String toAdd = result.getString("name_subject");
            res.add(id_t+"-"+toAdd);
        }
        return res;
    }
}
