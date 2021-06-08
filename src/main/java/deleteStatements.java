import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class deleteStatements{
    public static void deleteDatabase() throws IOException, SQLException {
        insertStatements.checkPath();
        File myObj = new File("accessRep.txt");
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
        myObj = new File("dekanat_database.accdb");
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
//    public static void deleteStudent(Student student) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "DELETE FROM student\n" +
//                "WHERE last_name = ? AND first_name = ?;";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setString(1, student.getLast_name());
//        st.setString(2, student.getFirst_name());
//        st.executeUpdate();
//    }
//    public static void deleteSubject(Subject subject) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "DELETE FROM subject\n" +
//                "WHERE id_subject = ?;";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, subject.getId_subject());
//        st.executeUpdate();
//    }
//    //can`t do (need to talk about)
//    public static void deleteGroup(Group_st gr) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "DELETE FROM group_st\n" +
//                "WHERE id_group = ?;";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, gr.getId_group());
//        st.executeUpdate();
//    }
//    public static void deleteTeacher(Teacher tc) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "DELETE FROM group_st\n" +
//                "WHERE id_teacher = ?;";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, tc.getId_teacher());
//        st.executeUpdate();
//    }
//    public static void deleteVid(Data_exam de) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "DELETE FROM data_exam\n" +
//                "WHERE id_data_exam = ?;";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, de.getId_data_exam());
//        st.executeUpdate();
//    }
//    public static void deleteBih(Bihunets de) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "DELETE FROM bihunets\n" +
//                "WHERE id_bih = ?;";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, de.getId_bih());
//        st.executeUpdate();
//    }
//    public static void deleteStFromGroup(Student stu, Group_st g) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "DELETE FROM mark_vid\n" +
//                "WHERE stud_id = ? AND id_data_exam IN\n" +
//                " (SELECT id_data_exam\n" +
//                "FROM data_exam\n" +
//                "WHERE id_group = ?);";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, stu.getStud_id());
//        st.setInt(2, g.getId_group());
//        st.executeUpdate();
//    }
//    public static void deleteStFromSubject(Student stu, Subject sb) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "DELETE FROM mark_vid\n" +
//                "WHERE stud_id = ? AND id_data_exam IN\n" +
//                " (SELECT id_data_exam\n" +
//                "FROM data_exam\n" +
//                "WHERE id_group IN\n" +
//                "SELECT id_group\n" +
//                "FROM group_st\n" +
//                "WHERE id_subject = ?);";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, stu.getStud_id());
//        st.setInt(2, sb.getId_subject());
//        st.executeUpdate();
//    }
//    public static void deleteTcFromGroup(Teacher tc, Group_st g) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "DELETE FROM data_exam\n" +
//                "WHERE id_teacher = ? AND id_group = ?\n;";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, tc.getId_teacher());
//        st.setInt(2, g.getId_group());
//        st.executeUpdate();
//    }
//    public static void deleteTcFromSubject(Teacher tc, Subject sb) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "DELETE FROM data_exam\n" +
//                "WHERE id_teacher = ? AND id_group IN\n" +
//                " (SELECT id_group\n" +
//                "FROM group_st\n" +
//                "WHERE id_subject = ?);";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, tc.getId_teacher());
//        st.setInt(2, sb.getId_subject());
//        st.executeUpdate();
//    }
}
