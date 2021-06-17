import Entities.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            accessRep.close();
        } catch (FileNotFoundException e) {
            System.out.println("You haven`t specified a file");
            checker.checkAndCreate();
        }
    }
    public static void insertStudent(Student student) throws IOException, SQLException {
        String sql = "INSERT INTO student (last_name, first_name, father_name, recordbook_no)\n" +
                "VALUES (?, ?,?,?);";
        PreparedStatement st = Database.connection.prepareStatement (sql);
        st.setString(1, student.getLast_name());
        st.setString(2, student.getFirst_name());
        st.setString(3, student.getFather_name());
        st.setString(4, student.getRecordbook_no());
        st.executeUpdate();
        }
    public static void insertTeacher(Teacher teacher) throws IOException, SQLException {
        String sql = "INSERT INTO teacher (last_name, first_name, father_name, position,science_degree,academ_status)\n" +
                "VALUES (?, ?,?,?,?,?);";
        PreparedStatement st = Database.connection.prepareStatement (sql);
        st.setString(1, teacher.getLast_name());
        st.setString(2, teacher.getFirst_name());
        st.setString(3, teacher.getFather_name());
        st.setString(4, teacher.getPosition());
        st.setString(5, teacher.getScience_degree());
        st.setString(6, teacher.getAcadem_status());
        st.executeUpdate();
    }
    public static void insertSubject(Subject subject) throws IOException, SQLException {
        String sql = "INSERT INTO subject (name_subject, edu_level, faculty)\n" +
                "VALUES (?, ?,?);";
        PreparedStatement st = Database.connection.prepareStatement (sql);
        st.setString(1, subject.getName_subject());
        st.setString(2, subject.getEdu_level());
        st.setString(3, subject.getFaculty());
        st.executeUpdate();
    }
    public static void insertGroup(Group_st gr) throws IOException, SQLException {
        String sql = "INSERT INTO group_st (name_group, year_study, semester,year_student,id_subject)\n" +
                "VALUES (?, ?,?,?,?);";
        PreparedStatement st = Database.connection.prepareStatement (sql);
        st.setString(1, gr.getName_group());
        st.setInt(2, gr.getYear_study());
        st.setInt(3, gr.getSemester());
        st.setInt(4, gr.getYear_student());
        st.setInt(5, gr.getId_subject());
        st.executeUpdate();
    }
    public static void insertBihunets(Bihunets bih) throws IOException, SQLException {
        String sql = "INSERT INTO bihunets (id_bih, date_taken, ok_till, reason,control,id_teacher)\n" +
                "VALUES (?, ?,?,?,?,?);";
        PreparedStatement st = Database.connection.prepareStatement (sql);
        st.setLong(1,bih.getId_bih());
        st.setDate(2, bih.getDate());
        st.setDate(3, bih.getOk_till());
        st.setString(4, bih.getReason());
        st.setString(5, bih.getControl());
        st.setInt(6, bih.getId_teacher());
        st.executeUpdate();
    }
    public static void insertDataExam(Data_exam dat) throws IOException, SQLException {
        String sql = "INSERT INTO data_exam (id_data_exam, num_present, num_absent, num_not_allowed,type_control,date_exam,id_group,id_teacher)\n" +
                "VALUES (?, ?,?,?,?,?,?,?);";
        PreparedStatement st = Database.connection.prepareStatement (sql);
        st.setLong(1, dat.getId_data_exam());
        st.setInt(2, dat.getNum_present());
        st.setInt(3, dat.getNum_absent());
        st.setInt(4, dat.getNum_not_allowed());
        st.setString(5, dat.getType_control());
        st.setDate(6, dat.getDate_exam());
        st.setInt(7,dat.getId_group());
        st.setInt(8,dat.getId_teacher());
        st.executeUpdate();
    }
    public static void insertMarkBih(Mark_bih mB) throws IOException, SQLException {
        System.out.println(mB.getId_mark_bih());
        System.out.println(mB.getId_mark_vid());
        System.out.println(mB.getMark_check());
        String sql = "INSERT INTO mark_bih (mark_sem, mark_exam, mark_tog,mark_nat,mark_ekts,id_mark_vid,id_bih)\n" +
                "VALUES (?, ?,?,?,?,?,?);";
        PreparedStatement st = Database.connection.prepareStatement (sql);
        st.setInt(1, mB.getMark_sem());
        st.setInt(2, mB.getMark_check());
        st.setInt(3, mB.getMark_raz());
        st.setString(4, mB.getMark_nat());
        st.setString(5, mB.getMark_ekts());
        st.setInt(6,mB.getId_mark_vid());
        st.setInt(7,mB.getId_bih());
        st.executeUpdate();
    }
    public static void insertMarkVid(Mark_vid mv) throws IOException, SQLException {
        String sql = "INSERT INTO mark_vid (mark_sem, mark_exam, mark_tog, mark_nat, mark_ekts, stud_id, id_data_exam)\n" +
                "VALUES (?, ?,?,?,?,?,?);";
        PreparedStatement st = Database.connection.prepareStatement (sql);
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
