import java.io.IOException;
import java.sql.*;

public class sqlRequests {
    public static ResultSet getStudentsBySubject(int Sub) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT stud_id, first_name+' '+last_name AS name_surname\n" +
                "FROM student\n" +
                "WHERE stud_id IN\n" +
                "(SELECT stud_id\n" +
                "FROM mark_vid\n" +
                "WHERE id_data_exam IN\n" +
                "(SELECT id_data_exam\n" +
                "FROM data_exam\n" +
                "WHERE id_group IN\n" +
                "(SELECT id_group\n" +
                "FROM group_st\n" +
                "WHERE id_subject = ?)));\n";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setInt(1, Sub);
        return st.executeQuery();
    }
    public static ResultSet getStudentsByGroup(int gr) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "PARAMETERS [group_] INTEGER;\n" +
                "SELECT stud_id, first_name+' '+last_name AS name_surname\n" +
                "FROM student\n" +
                "WHERE stud_id IN\n" +
                "(SELECT stud_id\n" +
                "FROM mark_vid\n" +
                "WHERE id_data_exam IN\n" +
                "(SELECT id_data_exam\n" +
                "FROM data_exam\n" +
                "WHERE id_group =?\n" +
                "));\n";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setInt(1, gr);
        return st.executeQuery();
    }
    public static ResultSet getStudentsByTeacher(int teacher) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT stud_id, first_name+' '+last_name AS name_surname\n" +
                "FROM student\n" +
                "WHERE stud_id IN\n" +
                "(SELECT stud_id\n" +
                "FROM mark_vid\n" +
                "WHERE id_data_exam IN\n" +
                "(SELECT id_data_exam\n" +
                "FROM data_exam\n" +
                "WHERE id_teacher =?\n" +
                "));\n";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setInt(1, teacher);
        return st.executeQuery();
    }
    public static ResultSet getStudentsByTCourse(int course) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT stud_id, first_name+' '+last_name AS name_surname\n" +
                "FROM student\n" +
                "WHERE stud_id IN\n" +
                "(SELECT stud_id\n" +
                "FROM mark_vid\n" +
                "WHERE id_data_exam IN\n" +
                "(SELECT id_data_exam\n" +
                "FROM data_exam\n" +
                "WHERE id_group IN\n" +
                "(SELECT id_group\n" +
                "FROM group_st\n" +
                "WHERE year_student = ?)\n" +
                "));\n";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setInt(1, course);
        return st.executeQuery();
    }
    public static ResultSet getVidomistByTeacher(int t) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT *\n" +
                "FROM data_exam\n" +
                "WHERE id_teacher = ?;\n";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setInt(1, t);
        return st.executeQuery();
    }
    public static ResultSet getVidomistByGroup(int g) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT *\n" +
                "FROM data_exam\n" +
                "WHERE id_group = ?;\n";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setInt(1, g);
        return st.executeQuery();
    }
    public static ResultSet getVidomistBySubject(int s) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT *\n" +
                "FROM data_exam\n" +
                "WHERE id_group IN\n" +
                " (SELECT id_group\n" +
                "FROM group_st\n" +
                "WHERE id_subject=?);";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setInt(1, s);
        return st.executeQuery();
    }
    public static ResultSet getDebtorByGroup(int g) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT stud_id, first_name+\" \"+last_name\n" +
                "FROM student\n" +
                "WHERE stud_id IN\n" +
                "(SELECT stud_id\n" +
                "FROM mark_vid\n" +
                "WHERE mark_ekts = 'F' AND NOT EXISTS\n" +
                "(SELECT *\n" +
                "FROM mark_bih\n" +
                "WHERE mark_vid.id_mark_vid = id_mark_vid)\n" +
                "AND id_data_exam IN\n" +
                "(SELECT id_data_exam\n" +
                "FROM data_exam\n" +
                "WHERE id_group = ?));\n";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setInt(1, g);
        return st.executeQuery();
    }
    public static ResultSet getDebtorBySubject(int s) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT stud_id, first_name+\" \"+last_name\n" +
                "FROM student\n" +
                "WHERE stud_id IN\n" +
                "(SELECT stud_id\n" +
                "FROM mark_vid\n" +
                "WHERE mark_ekts = 'F' AND NOT EXISTS\n" +
                "(SELECT *\n" +
                "FROM mark_bih\n" +
                "WHERE mark_vid.id_mark_vid = id_mark_vid)\n" +
                "AND id_data_exam IN\n" +
                "(SELECT id_data_exam\n" +
                "FROM data_exam\n" +
                "WHERE id_group IN\n" +
                "(SELECT id_group\n" +
                "FROM group_st\n" +
                "WHERE id_subject = ?)));";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setInt(1, s);
        return st.executeQuery();
    }
    public static ResultSet getDebtorByTeacher(int t) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT stud_id, first_name+\" \"+last_name\n" +
                "FROM student\n" +
                "WHERE stud_id IN\n" +
                "(SELECT stud_id\n" +
                "FROM mark_vid\n" +
                "WHERE mark_ekts = 'F' AND NOT EXISTS\n" +
                "(SELECT *\n" +
                "FROM mark_bih\n" +
                "WHERE mark_vid.id_mark_vid = id_mark_vid)\n" +
                "AND id_data_exam IN\n" +
                "(SELECT id_data_exam\n" +
                "FROM data_exam\n" +
                "WHERE id_teacher = ?));\n";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setInt(1, t);
        return st.executeQuery();
    }
    public static ResultSet getDebtorByCourse(int c) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT stud_id, first_name+\" \"+last_name\n" +
                "FROM student\n" +
                "WHERE stud_id IN\n" +
                "(SELECT stud_id\n" +
                "FROM mark_vid\n" +
                "WHERE mark_ekts = 'F' AND NOT EXISTS\n" +
                "(SELECT *\n" +
                "FROM mark_bih\n" +
                "WHERE mark_vid.id_mark_vid = id_mark_vid)\n" +
                "AND id_data_exam IN\n" +
                "(SELECT id_data_exam\n" +
                "FROM data_exam\n" +
                "WHERE id_group IN\n" +
                "(SELECT id_group\n" +
                "FROM group_st\n" +
                "WHERE year_student = ?)));";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setInt(1, c);
        return st.executeQuery();
    }
}
