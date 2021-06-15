import Entities.*;

import java.io.IOException;
import java.sql.*;

public class getIdsIfExists {
    public static int getTeacherId(Teacher t) throws IOException, SQLException {
        String sql = "SELECT id_teacher\n" +
                "FROM teacher\n" +
                "WHERE first_name=? AND last_name=?;";
        PreparedStatement st = Database.connection.prepareStatement (sql);
        st.setString(1, t.getFirst_name());
        st.setString(2, t.getLast_name());
        ResultSet result = st.executeQuery();
        int id =0;
        while (result.next()) {
                id = result.getInt("id_teacher");
            }
        return id;
    }
    public static int getStudentId(Student t) throws IOException, SQLException {
        String sql = "SELECT stud_id\n" +
                "FROM student\n" +
                "WHERE first_name=? AND last_name=?;";
        PreparedStatement st = Database.connection.prepareStatement (sql);
        st.setString(1, t.getFirst_name());
        st.setString(2, t.getLast_name());
        ResultSet result = st.executeQuery();
        int id =0;
        while (result.next()) {
            id = result.getInt("stud_id");
        }
        return id;
    }
    public static int getGroupId(Group_st t) throws IOException, SQLException {
        String sql = "SELECT id_group\n" +
                "FROM group_st\n" +
                "WHERE name_group=? AND year_study=? AND id_subject = ? AND semester = ?;";
        PreparedStatement st = Database.connection.prepareStatement (sql);
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
    public static int getSubjectId(Subject t) throws IOException, SQLException {
        String sql = "SELECT id_subject\n" +
                "FROM subject\n" +
                "WHERE name_subject = ? AND edu_level = ? AND faculty = ?;";
        PreparedStatement st = Database.connection.prepareStatement (sql);
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
    public static int getDataExamId(Data_exam t) throws IOException, SQLException {
        String sql = "SELECT id_data_exam\n" +
                "FROM data_exam\n" +
                "WHERE id_data_exam = ?;";
        PreparedStatement st = Database.connection.prepareStatement (sql);
        st.setLong(1, t.getId_data_exam());
        ResultSet result = st.executeQuery();
        int id =0;
        while (result.next()) {
            id = result.getInt("id_data_exam");
        }
        return id;
    }
    public static int getBihId(Bihunets t) throws IOException, SQLException {
        String sql = "SELECT id_bih\n" +
                "FROM bihunets\n" +
                "WHERE id_bihunets = ?;";
        PreparedStatement st = Database.connection.prepareStatement (sql);
        st.setLong(1, t.getId_bih());
        ResultSet result = st.executeQuery();
        int id =0;
        while (result.next()) {
            id = result.getInt("id_teacher");
        }
        return id;
    }
    public static int getMarkBih(Mark_bih t) throws IOException, SQLException {
        String sql = "SELECT id_mark_bih\n" +
                "FROM mark_bih\n" +
                "WHERE id_bih=? AND id_mark_vid=?;";
        PreparedStatement st = Database.connection.prepareStatement (sql);
        st.setInt(1, t.getId_bih());
        st.setInt(2, t.getId_mark_bih());
        ResultSet result = st.executeQuery();
        int id =0;
        while (result.next()) {
            id = result.getInt("id_teacher");
        }
        return id;
    }
    public static int getMarkVid(Mark_vid t) throws IOException, SQLException {
        String sql = "SELECT id_mark_vid\n" +
                "FROM mark_vid\n" +
                "WHERE stud_id=? AND id_data_exam=?;";
        PreparedStatement st = Database.connection.prepareStatement (sql);
        st.setInt(1, t.getStud_id());
        st.setInt(2, t.getId_data_exam());
        ResultSet result = st.executeQuery();
        int id =0;
        while (result.next()) {
            id = result.getInt("id_mark_vid");
        }
        return id;
    }
}
