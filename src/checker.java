import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Database.FileFormat;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import java.sql.*;

public class checker {
    static void checkAndCreate() throws IOException, SQLException {
        File accessReposit = new File("accessRep.txt");
        Scanner accessRep = null;
        String path = null;
        try {
            accessRep = new Scanner(accessReposit);
            path = accessRep.next();
        } catch (FileNotFoundException e) {
            //  e.printStackTrace();
            //TODO: dialog window to choose directory
            path = "dekanat_database.accdb";
            Database db = DatabaseBuilder.create(FileFormat.V2016, new File(path));
            System.out.println("The database file has been created.");
            accessReposit.createNewFile();
            FileWriter myWriter = new FileWriter("accessRep.txt");
            myWriter.write(path);
            myWriter.close();
            String d = "jdbc:ucanaccess://"+ path;
            Connection connection = DriverManager.getConnection(d);

            String sql = "CREATE TABLE student (\n" +
                    "stud_id AUTOINCREMENT NOT NULL,\n" +
                    "last_name text(255) NOT NULL,\n" +
                    "first_name text(255) NOT NULL,\n" +
                    "father_name text(255) NULL,\n" +
                    "recordbook_no int NOT NULL UNIQUE,\n" +
                    "PRIMARY KEY (stud_id)\n" +
                    ")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            sql = "CREATE TABLE teacher (\n" +
                    "id_teacher AUTOINCREMENT NOT NULL,\n" +
                    "last_name text(255) NOT NULL,\n" +
                    "first_name text(255) NOT NULL,\n" +
                    "father_name text(255) NULL,\n" +
                    "position text(255) NULL,\n" +
                    "science_degree text(255) NULL,\n" +
                    "academ_status text(255) NULL,\n" +
                    "PRIMARY KEY (id_teacher)\n" +
                    ")";
            statement = connection.createStatement();
            statement.executeUpdate(sql);

            sql = "CREATE TABLE subject (\n" +
                    "id_subject AUTOINCREMENT NOT NULL,\n" +
                    "name_subject text(255) NOT NULL,\n" +
                    "edu_level text(255) NOT NULL,\n" +
                    "faculty text(255) NOT NULL,\n" +
                    "PRIMARY KEY (id_subject)\n" +
                    ")";
            statement = connection.createStatement();
            statement.executeUpdate(sql);

            sql = "CREATE TABLE group_st (\n" +
                    "id_group AUTOINCREMENT NOT NULL,\n" +
                    "name_group text(255) NOT NULL,\n" +
                    "year_study int NOT NULL,\n" +
                    "semester int NOT NULL,\n" +
                    "year_student int NOT NULL,\n" +
                    "id_subject long NOT NULL,\n" +
                    "PRIMARY KEY (id_group),\n" +
                    "FOREIGN KEY (id_subject) REFERENCES subject (id_subject)\n" +
                    ")";
            statement = connection.createStatement();
            statement.executeUpdate(sql);

            sql = "CREATE TABLE bihunets(\n" +
                    "id_bih AUTOINCREMENT NOT NULL,\n" +
                    "date_taken date NOT NULL,\n" +
                    "ok_till date NOT NULL,\n" +
                    "reason text(255) NOT NULL,\n" +
                    "control text(255) NOT NULL,\n" +
                    "id_teacher long NOT NULL,\n" +
                    "PRIMARY KEY (id_bih),\n" +
                    "FOREIGN KEY (id_teacher) REFERENCES teacher (id_teacher)\n" +
                    ")";
            statement = connection.createStatement();
            statement.executeUpdate(sql);

            sql = "CREATE TABLE data_exam (\n" +
                    "id_data_exam AUTOINCREMENT NOT NULL,\n" +
                    "num_present int NOT NULL,\n" +
                    "num_absent int NOT NULL,\n" +
                    "num_not_allowed int NOT NULL,\n" +
                    "type_control text(255) NOT NULL,\n" +
                    "date_exam date NOT NULL,\n" +
                    "id_group long NOT NULL,\n" +
                    "id_teacher long NOT NULL,\n" +
                    "PRIMARY KEY (id_data_exam),\n" +
                    "FOREIGN KEY (id_teacher) REFERENCES teacher (id_teacher),\n" +
                    "FOREIGN KEY (id_group) REFERENCES group_st (id_group)\n" +
                    ")";
            statement = connection.createStatement();
            statement.executeUpdate(sql);

            sql = "CREATE TABLE mark_vid (\n" +
                    "id_mark_vid AUTOINCREMENT NOT NULL,\n" +
                    "mark_sem double NOT NULL,\n" +
                    "mark_exam double NOT NULL,\n" +
                    "mark_tog int NOT NULL,\n" +
                    "mark_nat text(255) NOT NULL,\n" +
                    "mark_ekts text(255) NOT NULL,\n" +
                    "stud_id long NOT NULL,\n" +
                    "id_data_exam long NOT NULL,\n" +
                    "PRIMARY KEY (id_mark_vid),\n" +
                    "FOREIGN KEY (id_data_exam) REFERENCES data_exam (id_data_exam),\n" +
                    "FOREIGN KEY (stud_id) REFERENCES student (stud_id)\n" +
                    ")";
            statement = connection.createStatement();
            statement.executeUpdate(sql);

            sql = "CREATE TABLE mark_bih (\n" +
                    "id_mark_bih AUTOINCREMENT NOT NULL,\n" +
                    "mark_sem double NOT NULL,\n" +
                    "mark_exam double NOT NULL,\n" +
                    "mark_tog int NOT NULL,\n" +
                    "mark_nat text(255) NOT NULL,\n" +
                    "mark_ekts text(255) NOT NULL,\n" +
                    "id_mark_vid long NOT NULL,\n" +
                    "id_bih long NOT NULL,\n" +
                    "PRIMARY KEY (id_mark_bih ),\n" +
                    "FOREIGN KEY (id_mark_vid) REFERENCES mark_vid (id_mark_vid),\n" +
                    "FOREIGN KEY (id_bih) REFERENCES bihunets (id_bih)\n" +
                    ")";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        System.out.println(path);
    }
}
