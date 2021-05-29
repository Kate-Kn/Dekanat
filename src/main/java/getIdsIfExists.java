import Entities.Group_st;
import Entities.Student;
import Entities.Subject;
import Entities.Teacher;

import java.io.IOException;
import java.sql.*;

public class getIdsIfExists {
    public int getTeacherId(Teacher t) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT id_teacher\n" +
                "FROM teacher\n" +
                "WHERE first_name=? AND last_name=?;";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setString(1, t.getFirst_name());
        st.setString(1, t.getLast_name());
        ResultSet result = st.executeQuery();
        int id =0;
        while (result.next()) {
                id = result.getInt("id_teacher");
            }
        return id;
    }
    public int getStudentId(Student t) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT stud_id\n" +
                "FROM student\n" +
                "WHERE first_name=? AND last_name=?;";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setString(1, t.getFirst_name());
        st.setString(1, t.getLast_name());
        ResultSet result = st.executeQuery();
        int id =0;
        while (result.next()) {
            id = result.getInt("stud_id");
        }
        return id;
    }
    public int getGroupId(Group_st t) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT id_teacher\n" +
                "FROM teacher\n" +
                "WHERE name_group=? AND year_study=? AND id_subject = ? AND semester = ?;";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setString(1, t.getName_group());
        st.setInt(2, t.getYear_study());
        st.setInt(3, t.getId_subject());
        st.setInt(4, t.getSemester());
        ResultSet result = st.executeQuery();
        int id =0;
        while (result.next()) {
            id = result.getInt("id_group");
        }
        return id;
    }
    public int getSubjectId(Subject t) throws IOException, SQLException {
        insertStatements.checkPath();
        String d = "jdbc:ucanaccess://"+ insertStatements.path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "SELECT id_teacher\n" +
                "FROM teacher\n" +
                "WHERE name_subject = ? AND edu_level = ? AND faculty = ?;";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setString(1, t.getName_subject());
        st.setString(2, t.getEdu_level());
        st.setString(3, t.getFaculty());
        ResultSet result = st.executeQuery();
        int id =0;
        while (result.next()) {
            id = result.getInt("id_subject");
        }
        return id;
    }
}
