import Entities.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class insertStatements {
    static String path = null;
    public static void checkPath() throws IOException, SQLException {
        File accessReposit = new File("accessRep.txt");
        Scanner accessRep = null;
        try {
            accessRep = new Scanner(accessReposit);
            path = accessRep.next();
        } catch (FileNotFoundException e) {
            System.out.println("You haven`t specified a file");
            checker.checkAndCreate();
        }
    }
    public static void insertStudent(Student student) throws IOException, SQLException {
        checkPath();
        String d = "jdbc:ucanaccess://"+ path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "INSERT INTO student (last_name, first_name, father_name, recordbook_no)\n" +
                "VALUES (?, ?,?,?);";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setString(1, student.getLast_name());
        st.setString(2, student.getFirst_name());
        st.setString(3, student.getFather_name());
        st.setString(4, student.getRecordbook_no());
        st.executeUpdate();
        }
    public static void insertTeacher(Teacher teacher) throws IOException, SQLException {
        checkPath();
        String d = "jdbc:ucanaccess://"+ path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "INSERT INTO teacher (last_name, first_name, father_name, position,science_degree,academ_status)\n" +
                "VALUES (?, ?,?,?,?,?);";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setString(1, teacher.getLast_name());
        st.setString(2, teacher.getFirst_name());
        st.setString(3, teacher.getFather_name());
        st.setString(4, teacher.getPosition());
        st.setString(5, teacher.getScience_degree());
        st.setString(6, teacher.getAcadem_status());
        st.executeUpdate();
    }
    public static void insertSubject(Subject subject) throws IOException, SQLException {
        checkPath();
        String d = "jdbc:ucanaccess://"+ path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "INSERT INTO subject (name_subject, edu_level, faculty)\n" +
                "VALUES (?, ?,?);";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setString(1, subject.getName_subject());
        st.setString(2, subject.getEdu_level());
        st.setString(3, subject.getFaculty());
        st.executeUpdate();
    }
    public static void insertGroup(Group_st gr) throws IOException, SQLException {
        checkPath();
        String d = "jdbc:ucanaccess://"+ path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "INSERT INTO grou_st (name_group, year_study, semester,year_student,id_subject)\n" +
                "VALUES (?, ?,?,?,?);";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setString(1, gr.getName_group());
        st.setInt(2, gr.getYear_study());
        st.setInt(3, gr.getSemester());
        st.setInt(4, gr.getYear_student());
        st.setInt(5, gr.getId_subject());
        st.executeUpdate();
    }
    public static void insertBihunets(Bihunets bih) throws IOException, SQLException {
        checkPath();
        String d = "jdbc:ucanaccess://"+ path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "INSERT INTO bihunets (date_taken, ok_till, reason,control,id_teacherr)\n" +
                "VALUES (?, ?,?,?,?);";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setDate(1, bih.getDate());
        st.setDate(2, bih.getOk_till());
        st.setString(3, bih.getReason());
        st.setString(4, bih.getControl());
        st.setInt(5, bih.getId_teacher());
        st.executeUpdate();
    }
    public static void insertDataExam(Data_exam dat) throws IOException, SQLException {
        checkPath();
        String d = "jdbc:ucanaccess://"+ path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "INSERT INTO data_exam (num_present, num_absent, num_not_allowed,type_control,date_exam,id_group,id_teacher)\n" +
                "VALUES (?, ?,?,?,?,?,?);";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setInt(1, dat.getNum_present());
        st.setInt(2, dat.getNum_absent());
        st.setInt(3, dat.getNum_not_allowed());
        st.setString(4, dat.getType_control());
        st.setDate(5, dat.getDate_exam());
        st.setInt(6,dat.getId_group());
        st.setInt(7,dat.getId_teacher());
        st.executeUpdate();
    }
    public static void insertMarkBih(Mark_bih mB) throws IOException, SQLException {
        checkPath();
        String d = "jdbc:ucanaccess://"+ path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "INSERT INTO data_exam (mark_sem, mark_exam, mark_tog,mark_nat,mark_ekts,id_mark_vid,id_bih)\n" +
                "VALUES (?, ?,?,?,?,?,?);";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setInt(1, mB.getMark_sem());
        st.setInt(2, mB.getMark_check());
        st.setInt(3, mB.getMark_raz());
        st.setString(4, mB.getMark_nat());
        st.setString(5, mB.getMark_ekts());
        st.setInt(6,mB.getId_mark_bih());
        st.setInt(7,mB.getId_bih());
        st.executeUpdate();
    }
    public static void insertMarkVid(Mark_vid mv) throws IOException, SQLException {
        checkPath();
        String d = "jdbc:ucanaccess://"+ path;
        Connection connection = DriverManager.getConnection(d);
        String sql = "INSERT INTO data_exam (mark_sem, mark_exam, mark_tog,mark_nat,mark_ekts,stid_id,id_data_exam)\n" +
                "VALUES (?, ?,?,?,?,?,?);";
        PreparedStatement st = connection.prepareStatement (sql);
        st.setInt(1, mv.getMark_sem());
        st.setInt(2, mv.getMark_check());
        st.setInt(3, mv.getMark_raz());
        st.setString(4, mv.getMark_nat());
        st.setString(5, mv.getMark_ekts());
        st.setInt(6,mv.getStud_id());
        st.setInt(7,mv.getId_data_exam());
        st.executeUpdate();
    }
    }
