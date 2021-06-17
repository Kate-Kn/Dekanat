import Entities.Student;
import Entities.Subject;

import java.sql.ResultSet;

public class main {
    public static void main(String[] args) throws Exception {
        checker.checkAndCreate();
        //mandatory line
        Database.connect();
       // ResultSet r = sqlRequests.getVidomistByFieldsInput(null, null, 0, null);
        ResultSet r2 = sqlRequests.statisticsInput("null", null, null, 0);
        ResultSet r3 = sqlRequests.getRetakeForFieldsInput("null", 0, null);
        ResultSet r = sqlRequests.getStudentsByFieldsInput("null", null, 0);
        ResultSet r1 = sqlRequests.getDebtorByFieldsInput("null", null, 0,null);
        ResultSet r4 = sqlRequests.getVidomistByFieldsInput("null", null, 0,null);
        ResultSet r5 = sqlRequests.getNumOfNedInput(0, "null", null,null);
//        String s ="ss";
//        s+="ddf";
//        System.out.println(s);
        //ResultSet r = sqlRequests.getStudentsByFields(1, 0, 0);
        //ResultSet r1 = sqlRequests.getVidomistByFields(0, 1, 2, 1);
        //ResultSet r2 = sqlRequests.statistics(2, 3, 0, 0);
        //ResultSet r3 = sqlRequests.getStudentsByFieldsInput("fg", null, 5);
        //checker.checkAndCreate();
        //deleteStatements.deleteDatabase();
        //Subject s = new Subject("sfd", "df", "f");
        //insertStatements.insertSubject(s);
        //insertStatements.insertGroup(new Group_st("sd", 22, 3, 3, 1));
        //System.out.println(getIdsIfExists.getSubjectId(s));
        MyParser parser = new MyParser("new.pdf");
//        MyParser parser3 = new MyParser("dc_good.pdf");
     //   MyParser parser4 = new MyParser("infoposhuk_good.pdf");
//        MyParser parser5 = new MyParser("unix_good.pdf");
//        MyParser parser6 = new MyParser("dc_good.pdf");

        // MyParser parser1= new MyParser("holes.pdf");
        Student student = new Student("dgjgfjd","dhhf","dhj","d");
        Subject subject= new Subject("jdh","djhc","jdh");
        getIdsIfExists.getMarkVidByStudent(student,subject);
        MyParser parser7 = new MyParser("nitbad.pdf");
        MyParser parser1 = new MyParser("C:\\Users\\Owner\\Documents\\Downloads\\Vidomosti_pdf\\Vidomosti_pdf\\From4Group_pdfs\\Бігунець.pdf");
  // MyParser parser2 = new MyParser("C:\\Users\\Owner\\Documents\\Downloads\\Vidomosti_pdf\\Vidomosti_pdf\\From4Group_pdfs\\Магістри_Іспит.pdf");
////
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
