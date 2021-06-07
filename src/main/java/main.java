import Entities.Subject;

import java.io.IOException;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws IOException, SQLException {


//        MyParser parser= new MyParser("C:\\Users\\Owner\\Documents\\Downloads\\new.pdf");
//
//        System.out.println("Hello");
//        Teacher t = new Teacher(1, "kate", "blavt", null,null,null,null);
//        System.out.println(  t.toString());
//
//        Teacher t1 = new Teacher(2, "kate2", "bl2avt", "volod",null,null,"prof");
//        System.out.println(t1.toString());

//        String databaseURL = "jdbc:ucanaccess://C://Users//Member//Downloads//marks_data_new_final.accdb";
//
//        try (Connection connection = DriverManager.getConnection(databaseURL)) {
//
//            String sql = "SELECT subject.id_subject, name_subject\r\n"
//                    + "FROM ((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam\r\n"
//                    + "WHERE faculty = 'інформатика'\r\n"
//                    + "GROUP BY subject.id_subject, name_subject\r\n"
//                    + "HAVING (SELECT COUNT(stud_id)\r\n"
//                    + "FROM student) = \r\n"
//                    + "(SELECT COUNT(stud_id)\r\n"
//                    + "FROM student\r\n"
//                    + "WHERE stud_id IN\r\n"
//                    + "(SELECT stud_id\r\n"
//                    + "FROM mark_vid\r\n"
//                    + "WHERE id_data_exam IN\r\n"
//                    + "(SELECT id_data_exam \r\n"
//                    + "FROM data_exam\r\n"
//                    + "WHERE id_group IN\r\n"
//                    + "(SELECT id_group\r\n"
//                    + "FROM group_st\r\n"
//                    + "WHERE subject.id_subject = group_st.id_subject))));\r\n"
//                    + "";
//
//            Statement statement = connection.createStatement();
//            ResultSet result = statement.executeQuery(sql);
//            while (result.next()) {
//                int id = result.getInt("id_subject");
//                String name = result.getString("name_subject");
//                System.out.println(id + ", " + name);
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
        checker.checkAndCreate();
        Subject s = new Subject("sfd", "df", "f");
        insertStatements.insertSubject(s);
        System.out.println(getIdsIfExists.getSubjectId(s));
        //test for insert statement
        //can be executed only once, because of recordbook is unique
//        Subject sb =new Subject(1,"ds","sd","sd");
//        Student st = new Student(2,"Bondar","Taras","",332);
//        //insertStatements.insertStudent(st);
//
//        ResultSet result =  sqlRequests.getStudentsBySubject(1);
//        while (result.next()) {
//                int id = result.getInt("id_subject");
//                String name = result.getString("name_subject");
//                System.out.println(id + ", " + name);
//            }
        //can't do this because in group: ON DELETE NO ACTION
        //deleteStatements.deleteSubject(sb);
    }
}
