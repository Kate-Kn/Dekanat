import java.io.IOException;
import java.sql.*;

public class sqlRequests {
    public static ResultSet getNumOfNedInput(int year, String subject, String teacher) throws IOException, SQLException {
        String sql = "SELECT Nz(SUM(num_not_allowed),0) AS кількість_недопусків\n" +
                "FROM (((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) " +
                "INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) " +
                "INNER JOIN teacher ON data_exam.id_teacher = teacher.id_teacher)\n" +
                "WHERE ";
        if(subject!=null)
        {
            sql+="name_subject like '%"+subject+"%' AND";
        }
        if(teacher!=null)
        {
            sql+=" teacher.last_name like '%"+teacher+"%' AND";
        }
        if(year!=0)
        {
            sql+=" year_student = "+year+" AND";
        }
        sql+=" 1=1";
        if(subject == null&&teacher == null &&year==0)
        {
            sql ="SELECT Nz(SUM(num_not_allowed),0) AS кількість_недопусків\n" +
                    "FROM ((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject)" +
                    " INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) " +
                    "INNER JOIN teacher ON data_exam.id_teacher = teacher.id_teacher\n"
            ;
        }
        Statement st = Database.connection.createStatement();
        return st.executeQuery(sql);
    }
    public static ResultSet getDebtorByFieldsInput(String student, String subject, int year, String teacher) throws IOException, SQLException {
        String sql ="SELECT student.stud_id, student.first_name+\" \"+student.last_name " +
                "AS ПІБ_студент, recordbook_no, year_student, subject.id_subject, " +
                "name_subject, teacher.id_teacher, teacher.first_name+\" \"+teacher.last_name" +
                " AS ПІБ_викладач, id_bih, id_mark_bih, mark_vid.id_mark_vid," +
                " mark_vid.mark_ekts\n" +
                "FROM (((((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject)" +
                " INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) " +
                "INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) " +
                "INNER JOIN student ON mark_vid.stud_id = student.stud_id) " +
                "INNER JOIN teacher ON data_exam.id_teacher = teacher.id_teacher) " +
                "LEFT JOIN mark_bih ON mark_bih.id_mark_vid = mark_vid.id_mark_vid\n" +
                "WHERE mark_vid.mark_ekts = 'F' AND NOT EXISTS\n" +
                "                (SELECT *\n" +
                "                FROM mark_bih\n" +
                "                WHERE mark_vid.id_mark_vid = id_mark_vid\n" +
                "                ) AND ";
        if(subject!=null)
        {
            sql+="name_subject like '%"+subject+"%' AND";
        }
        if(teacher!=null)
        {
            sql+=" teacher.last_name like '%"+teacher+"%' AND";
        }
        if(year!=0)
        {
            sql+=" year_student = "+year+" AND";
        }
        if (student != null)
        {
            sql+=" student.last_name like '%"+student+"%' AND";
        }
        sql+=" 1=1";
        if(subject == null&&teacher == null &&year==0&&student==null)
        {
            sql ="SELECT student.stud_id, " +
                    "student.first_name+\" \"+student.last_name AS ПІБ_студент, " +
                    "recordbook_no, year_student, subject.id_subject, " +
                    "name_subject, teacher.id_teacher, " +
                    "teacher.first_name+\" \"+teacher.last_name AS ПІБ_викладач, " +
                    "id_bih, id_mark_bih, mark_vid.id_mark_vid, mark_vid.mark_ekts\n" +
                    "FROM (((((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) " +
                    "INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) " +
                    "INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) " +
                    "INNER JOIN student ON mark_vid.stud_id = student.stud_id) " +
                    "INNER JOIN teacher ON data_exam.id_teacher = teacher.id_teacher) " +
                    "LEFT JOIN mark_bih ON mark_bih.id_mark_vid = mark_vid.id_mark_vid\n" +
                    "WHERE mark_vid.mark_ekts = 'F' AND NOT EXISTS\n" +
                    "                (SELECT *\n" +
                    "                FROM mark_bih\n" +
                    "                WHERE mark_vid.id_mark_vid = id_mark_vid\n" +
                    "              )";
        }
        Statement st = Database.connection.createStatement();
        return st.executeQuery(sql);
    }
    public static ResultSet getVidomistByFieldsInput(String teacher_surname, String subject, int year,String student) throws IOException, SQLException {
        String sql ="SELECT id_data_exam, num_present, num_absent, " +
                "num_not_allowed, type_control,date_exam, id_teacher," +
                "teacher.first_name+\" \"+teacher.last_name AS ПІБ_викладач" +
                ",student.stud_id,student.first_name+\" \"+student.last_name " +
                "AS  ПІБ_студент\n" +
                "FROM ((((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) INNER JOIN student ON mark_vid.stud_id = student.stud_id) INNER JOIN teacher ON data_exam.id_teacher = teacher.id_teacher\n" +
                "WHERE ";
        if(subject!=null)
        {
            sql+="name_subject like '%"+subject+"%' AND";
        }
        if(teacher_surname!=null)
        {
            sql+=" teacher.last_name like '%"+teacher_surname+"%' AND";
        }
        if(year!=0)
        {
            sql+=" year_student = "+year+" AND";
        }
        if (student != null)
        {
            sql+=" student.last_name like '%"+student+"%' AND";
        }
        sql+=" 1=1";
        if(subject == null&&teacher_surname == null &&year==0&&student==null)
        {
            sql ="SELECT id_data_exam, num_present, num_absent, num_not_allowed, " +
                    "type_control,date_exam, id_teacher,teacher.first_name+\" \"+" +
                    "teacher.last_name AS ПІБ_викладач,student.stud_id,student.first_name+\" \"+" +
                    "student.last_name AS AS ПІБ_студент\n" +
                    "FROM ((((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) " +
                    "INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) " +
                    "INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) " +
                    "INNER JOIN student ON mark_vid.stud_id = student.stud_id) " +
                    "INNER JOIN teacher ON data_exam.id_teacher = teacher.id_teacher\n";
        }
        Statement st = Database.connection.createStatement();
        return st.executeQuery(sql);
    }
    public static ResultSet getStudentsByFieldsInput(String subject, String teacher_surname, int year) throws IOException, SQLException {
        String sql = "SELECT student.stud_id, student.first_name+\" \"+student.last_name  " +
                "AS ПІБ_студент, recordbook_no,year_student, id_subject,name_subject " +
                "id_teacher, teacher.first_name+\" \"+teacher.last_name AS ПІБ_викладач \n" +
                "FROM ((((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) " +
                "INNER JOIN data_exam ON group_st.id_group = data_exam.id_group)" +
                " INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) " +
                "INNER JOIN student ON mark_vid.stud_id = student.stud_id) " +
                "INNER JOIN teacher ON data_exam.id_teacher = teacher.id_teacher\n" +
                "WHERE ";
        if(subject!=null)
        {
            sql+="name_subject like '%"+subject+"%' AND";
        }
        if(teacher_surname!=null)
        {
            sql+=" teacher.last_name like '%"+teacher_surname+"%' AND";
        }
        if(year!=0)
        {
            sql+=" year_student = "+year+" AND";
        }
        sql+=" 1=1";
        Statement st = Database.connection.createStatement();
        if(subject == null&&teacher_surname == null &&year==0)
        {
            sql = "SELECT student.stud_id, student.first_name+\" \"+student.last_name " +
                    "AS ПІБ_студент, recordbook_no,year_student, id_subject," +
                    "name_subject id_teacher, teacher.first_name+\" \"+teacher.last_name " +
                    "AS ПІБ_викладач \n" +
                    "FROM ((((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) " +
                    "INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) " +
                    "INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) " +
                    "INNER JOIN student ON mark_vid.stud_id = student.stud_id) " +
                    "INNER JOIN teacher ON data_exam.id_teacher = teacher.id_teacher\n";
        }
        return st.executeQuery(sql);
    }
    public static ResultSet getRetakeForFieldsInput(String subject, int year, String teacher) throws IOException, SQLException {
        String sql = "SELECT student.stud_id, student.first_name+\" \"+student.last_name " +
                "AS  ПІБ_студент, recordbook_no,year_student, subject.id_subject," +
                "name_subject,teacher.id_teacher,teacher.first_name+\" \"+teacher.last_name " +
                "AS ПІБ_викладач, id_bih,id_mark_bih\n" +
                "FROM (((((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) INNER JOIN student ON mark_vid.stud_id = student.stud_id) INNER JOIN teacher ON data_exam.id_teacher = teacher.id_teacher) INNER JOIN mark_bih ON mark_bih.id_mark_vid = mark_vid.id_mark_vid\n" +
                "WHERE stud_id IN\n" +
                "(SELECT stud_id\n" +
                "FROM mark_vid\n" +
                "WHERE id_mark_vid IN\n" +
                "(SELECT id_mark_vid \n" +
                "FROM mark_bih) \n" +
                "AND id_data_exam IN\n" +
                "(SELECT data_exam.id_data_exam\n" +
                "FROM ((((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) INNER JOIN student ON mark_vid.stud_id = student.stud_id) INNER JOIN teacher ON data_exam.id_teacher = teacher.id_teacher\n" +
                "WHERE \n";
        if(subject!=null)
        {
            sql+="name_subject like '%"+subject+"%' AND";
        }
        if(teacher!=null)
        {
            sql+=" teacher.last_name like '%"+teacher+"%' AND";
        }
        if(year!=0)
        {
            sql+=" year_student = "+year+" AND";
        }
        sql+=" 1=1))";
        if(subject == null&&teacher == null &&year==0)
        {
            sql = "SELECT student.stud_id, student.first_name+\" \"+student.last_name " +
                    "AS ПІБ_студент, recordbook_no,year_student, subject.id_subject," +
                    "name_subject,teacher.id_teacher," +
                    "teacher.first_name+\" \"+teacher.last_name AS ПІБ_викладач, " +
                    "id_bih,id_mark_bih\n" +
                    "FROM (((((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) INNER JOIN student ON mark_vid.stud_id = student.stud_id) INNER JOIN teacher ON data_exam.id_teacher = teacher.id_teacher) INNER JOIN mark_bih ON mark_bih.id_mark_vid = mark_vid.id_mark_vid\n" +
                    "WHERE stud_id IN\n" +
                    "(SELECT stud_id\n" +
                    "FROM mark_vid\n" +
                    "WHERE id_mark_vid IN\n" +
                    "(SELECT id_mark_vid \n" +
                    "FROM mark_bih) \n" +
                    "AND id_data_exam IN\n" +
                    "(SELECT data_exam.id_data_exam\n" +
                    "FROM ((((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) INNER JOIN student ON mark_vid.stud_id = student.stud_id) INNER JOIN teacher ON data_exam.id_teacher = teacher.id_teacher))\n"
            ;

        }
        Statement st = Database.connection.createStatement();
        return st.executeQuery(sql);
    }
    public static ResultSet statisticsInput(String subject, String teacher,String student,int year) throws IOException, SQLException {
        String sql = "SELECT student.stud_id, student.first_name+\" \"+student.last_name " +
                "AS ПІБ_студент, recordbook_no, mark_tog, subject.id_subject," +
                "year_student,name_subject,teacher.id_teacher," +
                "teacher.first_name+\" \"+teacher.last_name AS ПІБ_викладач \n" +
                "FROM ((((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) INNER JOIN student ON mark_vid.stud_id = student.stud_id) INNER JOIN teacher ON teacher.id_teacher = data_exam.id_teacher\n" +
                "WHERE ";
        if(subject!=null&& !subject.equals(""))
        {
            sql+="name_subject like '%"+subject+"%' AND";
        }
        if(teacher!=null&& !teacher.equals(""))
        {
            sql+=" teacher.last_name like '%"+teacher+"%' AND";
        }
        if(year!=0)
        {
            sql+=" year_student = "+year+" AND";
        }
        if(student!=null&& !student.equals(""))
        {
            sql+=" student.last_name like '%"+student+"%' AND";
        }
        sql+=" 1=1 \n ORDER BY mark_tog DESC;\n";
        if(subject == null&&teacher == null &&year==0&&student==null)
        {
            sql = "SELECT student.stud_id, student.first_name+\" \"+student.last_name " +
                    "AS ПІБ_студент, recordbook_no, mark_tog, subject.id_subject," +
                    "year_student,name_subject,teacher.id_teacher," +
                    "teacher.first_name+\" \"+teacher.last_name AS ПІБ_викладач \n" +
                    "FROM ((((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) INNER JOIN student ON mark_vid.stud_id = student.stud_id) INNER JOIN teacher ON teacher.id_teacher = data_exam.id_teacher\n" +
                    "ORDER BY mark_tog DESC;";
        }
        System.out.println(sql);
        Statement st = Database.connection.createStatement();
        return st.executeQuery(sql);
    }

    public static ResultSet getStudentsByFields(int sub, int t, int year) throws IOException, SQLException {
        String sql = "SELECT student.stud_id, first_name+\" \"+last_name " +
                "AS AS ПІБ_студент, recordbook_no, id_subject, id_teacher, " +
                "year_student \n" +
                "FROM ((((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) INNER JOIN student ON mark_vid.stud_id = student.stud_id) INNER JOIN teacher ON data_exam.id_teacher = teacher.id_teacher\n" +
                "WHERE ";
        if(sub!=0)
        {
            sql+="id_subject = "+sub+" AND";
        }
        if(t!=0)
        {
            sql+=" id_teacher = "+t+" AND";
        }
        if(year!=0)
        {
            sql+=" year_student = "+year+" AND";
        }
        sql+=" 1=1";
//        String sql = "SELECT stud_id, first_name+' '+last_name AS name_surname\n" +
//                "FROM student\n" +
//                "WHERE stud_id IN\n" +
//                "(SELECT stud_id\n" +
//                "FROM mark_vid\n" +
//                "WHERE id_data_exam IN\n" +
//                "(SELECT id_data_exam\n" +
//                "FROM data_exam\n" +
//                "WHERE id_group IN\n" +
//                "(SELECT id_group\n" +
//                "FROM group_st\n" +
//                "WHERE id_subject = ?)));\n";
        Statement st = Database.connection.createStatement();
        return st.executeQuery(sql);
    }

//    public static ResultSet getStudentsByGroup(int gr) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "SELECT stud_id, first_name+' '+last_name AS name_surname\n" +
//                "FROM student\n" +
//                "WHERE stud_id IN\n" +
//                "(SELECT stud_id\n" +
//                "FROM mark_vid\n" +
//                "WHERE id_data_exam IN\n" +
//                "(SELECT id_data_exam\n" +
//                "FROM data_exam\n" +
//                "WHERE id_group =?\n" +
//                "));\n";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, gr);
//        return st.executeQuery();
//    }
//    public static ResultSet getStudentsByTeacher(int teacher) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "SELECT stud_id, first_name+' '+last_name AS name_surname\n" +
//                "FROM student\n" +
//                "WHERE stud_id IN\n" +
//                "(SELECT stud_id\n" +
//                "FROM mark_vid\n" +
//                "WHERE id_data_exam IN\n" +
//                "(SELECT id_data_exam\n" +
//                "FROM data_exam\n" +
//                "WHERE id_teacher =?\n" +
//                "));\n";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, teacher);
//        return st.executeQuery();
//    }
//    public static ResultSet getStudentsByTCourse(int course) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "SELECT stud_id, first_name+' '+last_name AS name_surname\n" +
//                "FROM student\n" +
//                "WHERE stud_id IN\n" +
//                "(SELECT stud_id\n" +
//                "FROM mark_vid\n" +
//                "WHERE id_data_exam IN\n" +
//                "(SELECT id_data_exam\n" +
//                "FROM data_exam\n" +
//                "WHERE id_group IN\n" +
//                "(SELECT id_group\n" +
//                "FROM group_st\n" +
//                "WHERE year_student = ?)\n" +
//                "));\n";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, course);
//        return st.executeQuery();
//    }
    public static ResultSet getVidomistByFields(int t, int sub, int year,int s) throws IOException, SQLException {
        String sql ="SELECT id_data_exam, num_present, num_absent, num_not_allowed, type_control,date_exam, id_teacher\n" +
                "FROM (((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) INNER JOIN student ON mark_vid.stud_id = student.stud_id\n" +
                "WHERE ";
        if(sub!=0)
        {
            sql+="id_subject = "+sub+" AND";
        }
        if(t!=0)
        {
            sql+=" id_teacher = "+t+" AND";
        }
        if(year!=0)
        {
            sql+=" year_student = "+year+" AND";
        }
        if (s != 0)
        {
            sql+=" student.stud_id = "+s+" AND";
        }
        sql+=" 1=1";
        //"SELECT *\n" +
//                "FROM data_exam\n" +
//                "WHERE id_teacher = ?;\n";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, t);
        Statement st = Database.connection.createStatement();
        return st.executeQuery(sql);
    }

//    public static ResultSet getVidomistByGroup(int g) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "SELECT *\n" +
//                "FROM data_exam\n" +
//                "WHERE id_group = ?;\n";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, g);
//        return st.executeQuery();
//    }
//    public static ResultSet getVidomistBySubject(int s) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "SELECT *\n" +
//                "FROM data_exam\n" +
//                "WHERE id_group IN\n" +
//                " (SELECT id_group\n" +
//                "FROM group_st\n" +
//                "WHERE id_subject=?);";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, s);
//        return st.executeQuery();
//    }
    //all values are 0 not allowed
    public static ResultSet getDebtorByFields(int student, int subject, int year, int teacher) throws IOException, SQLException {
        String sql ="SELECT stud_id, first_name+\" \"+last_name AS surname_name\n" +
                "FROM student\n" +
                "WHERE stud_id IN\n" +
                "(SELECT student.stud_id\n" +
                "FROM (((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) INNER JOIN student ON mark_vid.stud_id = student.stud_id\n" +
                "WHERE NOT EXISTS\n" +
                "(SELECT *\n" +
                "FROM mark_bih\n" +
                "WHERE mark_vid.id_mark_vid = id_mark_vid)\n" +
                ") AND ;\n";
        if(subject!=0)
        {
            sql+="id_subject = "+subject+" AND";
        }
        if(teacher!=0)
        {
            sql+=" id_teacher = "+teacher+" AND";
        }
        if(year!=0)
        {
            sql+=" year_student = "+year+" AND";
        }
        if (student != 0)
        {
            sql+=" student.stud_id = "+student+" AND";
        }
        sql+=" 1=1";
//        String sql = "SELECT stud_id, first_name+\" \"+last_name\n" +
//                "FROM student\n" +
//                "WHERE stud_id IN\n" +
//                "(SELECT stud_id\n" +
//                "FROM mark_vid\n" +
//                "WHERE mark_ekts = 'F' AND NOT EXISTS\n" +
//                "(SELECT *\n" +
//                "FROM mark_bih\n" +
//                "WHERE mark_vid.id_mark_vid = id_mark_vid)\n" +
//                "AND id_data_exam IN\n" +
//                "(SELECT id_data_exam\n" +
//                "FROM data_exam\n" +
//                "WHERE id_group = ?));\n";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, g);
        Statement st = Database.connection.createStatement();
        return st.executeQuery(sql);
    }

//    public static ResultSet getDebtorBySubject(int s) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "SELECT stud_id, first_name+\" \"+last_name\n" +
//                "FROM student\n" +
//                "WHERE stud_id IN\n" +
//                "(SELECT stud_id\n" +
//                "FROM mark_vid\n" +
//                "WHERE mark_ekts = 'F' AND NOT EXISTS\n" +
//                "(SELECT *\n" +
//                "FROM mark_bih\n" +
//                "WHERE mark_vid.id_mark_vid = id_mark_vid)\n" +
//                "AND id_data_exam IN\n" +
//                "(SELECT id_data_exam\n" +
//                "FROM data_exam\n" +
//                "WHERE id_group IN\n" +
//                "(SELECT id_group\n" +
//                "FROM group_st\n" +
//                "WHERE id_subject = ?)));";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, s);
//        return st.executeQuery();
//    }
//    public static ResultSet getDebtorByTeacher(int t) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "SELECT stud_id, first_name+\" \"+last_name\n" +
//                "FROM student\n" +
//                "WHERE stud_id IN\n" +
//                "(SELECT stud_id\n" +
//                "FROM mark_vid\n" +
//                "WHERE mark_ekts = 'F' AND NOT EXISTS\n" +
//                "(SELECT *\n" +
//                "FROM mark_bih\n" +
//                "WHERE mark_vid.id_mark_vid = id_mark_vid)\n" +
//                "AND id_data_exam IN\n" +
//                "(SELECT id_data_exam\n" +
//                "FROM data_exam\n" +
//                "WHERE id_teacher = ?));\n";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, t);
//        return st.executeQuery();
//    }
//    public static ResultSet getDebtorByCourse(int c) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "SELECT stud_id, first_name+\" \"+last_name\n" +
//                "FROM student\n" +
//                "WHERE stud_id IN\n" +
//                "(SELECT stud_id\n" +
//                "FROM mark_vid\n" +
//                "WHERE mark_ekts = 'F' AND NOT EXISTS\n" +
//                "(SELECT *\n" +
//                "FROM mark_bih\n" +
//                "WHERE mark_vid.id_mark_vid = id_mark_vid)\n" +
//                "AND id_data_exam IN\n" +
//                "(SELECT id_data_exam\n" +
//                "FROM data_exam\n" +
//                "WHERE id_group IN\n" +
//                "(SELECT id_group\n" +
//                "FROM group_st\n" +
//                "WHERE year_student = ?)));";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, c);
//        return st.executeQuery();
//    }

//    public static ResultSet getNumOfNedForSubject(int s) throws IOException, SQLException {
//        String sql = "SELECT SUM(num_not_allowed) AS num_of_nedopusk\n" +
//                "FROM data_exam\n" +
//                "WHERE id_group IN\n" +
//                "(SELECT id_group\n" +
//                "FROM group_st\n" +
//                "WHERE id_subject = ?);";
//        PreparedStatement st = Database.connection.prepareStatement (sql);
//        st.setInt(1, s);
//        return st.executeQuery();
//    }
//    public static ResultSet getNumOfNedForTeacher(int t) throws IOException, SQLException {
//        String sql = "SELECT SUM(num_not_allowed) AS num_of_nedopusk\n" +
//                "FROM data_exam\n" +
//                "WHERE id_teacher =  ?;";
//        PreparedStatement st = Database.connection.prepareStatement (sql);
//        st.setInt(1, t);
//        return st.executeQuery();
//    }
//    public static ResultSet getNumOfNedForTeacher(String teacher_surname, String subject_name, String student_surname) throws IOException, SQLException {
//        String sql = "SELECT SUM(num_not_allowed) AS num_of_nedopusk\n" +
//                "FROM data_exam\n" +
//                "WHERE id_teacher =  ?;";
//        PreparedStatement st = Database.connection.prepareStatement (sql);
//        st.setInt(1, t);
//        return st.executeQuery();
//    }
    public static ResultSet getRetakeForFields(int subject, int year, int teacher) throws IOException, SQLException {
        String sql = "SELECT stud_id, first_name+\" \"+last_name AS name_surname\n" +
                "FROM student\n" +
                "WHERE stud_id IN\n" +
                "(SELECT stud_id\n" +
                "FROM mark_vid\n" +
                "WHERE id_mark_vid IN\n" +
                "(SELECT id_mark_vid \n" +
                "FROM mark_bih) \n" +
                "AND id_data_exam IN\n" +
                "(SELECT data_exam.id_data_exam\n" +
                "FROM (((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) INNER JOIN student ON mark_vid.stud_id = student.stud_id\n" +
                "WHERE \n";
//        String sql = "SELECT stud_id, first_name+\" \"+last_name AS name_surname\n" +
//                "FROM student\n" +
//                "WHERE stud_id IN\n" +
//                "(SELECT stud_id\n" +
//                "FROM mark_vid\n" +
//                "WHERE id_mark_vid IN\n" +
//                "(SELECT id_mark_vid \n" +
//                "FROM mark_bih) \n" +
//                "AND id_data_exam IN\n" +
//                "(SELECT id_data_exam\n" +
//                "FROM data_exam\n" +
//                "WHERE id_group IN\n" +
//                "(SELECT id_group\n" +
//                "FROM group_st\n" +
//                "WHERE id_subject = ?\n" +
//                ")));\n";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, s);
        if(subject!=0)
        {
            sql+="id_subject = "+subject+" AND";
        }
        if(teacher!=0)
        {
            sql+=" id_teacher = "+teacher+" AND";
        }
        if(year!=0)
        {
            sql+=" year_student = "+year+" AND";
        }
        sql+=" 1=1))";
        Statement st = Database.connection.createStatement();
        return st.executeQuery(sql);
    }

//    public static ResultSet getRetakeForCourse(int c) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "SELECT stud_id, first_name+\" \"+last_name AS name_surname\n" +
//                "FROM student\n" +
//                "WHERE stud_id IN\n" +
//                "(SELECT stud_id\n" +
//                "FROM mark_vid\n" +
//                "WHERE id_mark_vid IN\n" +
//                "(SELECT id_mark_vid \n" +
//                "FROM mark_bih) \n" +
//                "AND id_data_exam IN\n" +
//                "(SELECT id_data_exam\n" +
//                "FROM data_exam\n" +
//                "WHERE id_group IN\n" +
//                "(SELECT id_group\n" +
//                "FROM group_st\n" +
//                "WHERE year_student = ?\n" +
//                ")));\n";
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, c);
//        return st.executeQuery();
//    }
//    public static ResultSet getRetakeForTeacher(int t) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "SELECT stud_id, first_name+\" \"+last_name AS name_surname\n" +
//                "FROM student\n" +
//                "WHERE stud_id IN\n" +
//                "(SELECT stud_id\n" +
//                "FROM mark_vid\n" +
//                "WHERE id_mark_vid IN\n" +
//                "(SELECT id_mark_vid \n" +
//                "FROM mark_bih) \n" +
//                "AND id_data_exam IN\n" +
//                "(SELECT id_data_exam\n" +
//                "FROM data_exam\n" +
//                "WHERE id_teacher = ?\n" ;
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, t);
//        return st.executeQuery();
//    }
//    public static ResultSet getRetakeForGroup(int g) throws IOException, SQLException {
//        insertStatements.checkPath();
//        String d = "jdbc:ucanaccess://"+ insertStatements.path;
//        Connection connection = DriverManager.getConnection(d);
//        String sql = "SELECT stud_id, first_name+\" \"+last_name AS name_surname\n" +
//                "FROM student\n" +
//                "WHERE stud_id IN\n" +
//                "(SELECT stud_id\n" +
//                "FROM mark_vid\n" +
//                "WHERE id_mark_vid IN\n" +
//                "(SELECT id_mark_vid \n" +
//                "FROM mark_bih) \n" +
//                "AND id_data_exam IN\n" +
//                "(SELECT id_data_exam\n" +
//                "FROM data_exam\n" +
//                "WHERE id_group = ?))\n" ;
//        PreparedStatement st = connection.prepareStatement (sql);
//        st.setInt(1, g);
//        return st.executeQuery();
//    }
    public static ResultSet statistics(int subject, int teacher,int student,int year) throws IOException, SQLException {
        String sql = "SELECT student.stud_id, student.first_name+\" \"+student.last_name AS name_surname_student, recordbook_no, mark_tog, subject.id_subject,year_student,name_subject,teacher.id_teacher,teacher.first_name+\" \"+teacher.last_name AS " +
                "FROM ((((subject INNER JOIN group_st ON subject.id_subject =group_st.id_subject) INNER JOIN data_exam ON group_st.id_group = data_exam.id_group) INNER JOIN mark_vid ON mark_vid.id_data_exam = data_exam.id_data_exam) INNER JOIN student ON mark_vid.stud_id = student.stud_id) INNER JOIN teacher ON teacher.id_teacher = data_exam.id_teacher\n" +
                "WHERE ";

        if(subject!=0)
        {
            sql+="id_subject = "+subject+" AND";
        }
        if(teacher!=0)
        {
            sql+=" id_teacher = "+teacher+" AND";
        }
        if(year!=0)
        {
            sql+=" year_student = "+year+" AND";
        }
        if(student!=0)
        {
            sql+=" stud_id = "+student+" AND";
        }
        sql+=" 1=1 \n ORDER BY mark_tog;\n";
        Statement st = Database.connection.createStatement();
        return st.executeQuery(sql);
    }


}
