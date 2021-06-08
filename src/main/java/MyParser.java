import Entities.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static org.apache.pdfbox.pdfparser.PDFParser.load;

public  class MyParser {

    String path;

    public  MyParser(String path) throws IOException, SQLException {
        this.path=path;
        File file = new File(path);
        PDDocument document = load(file);

        PDFTextStripper pdfStripper = new PDFTextStripper();

        String text = pdfStripper.getText(document);
        String edu=StringUtils.substringBetween(text,"рівень", "Факультет");

        String fac=StringUtils.substringBetween(text,"Факультет", "Рік");
        String year=StringUtils.substringBetween(text,"навчання", "Група");

        String grouup =StringUtils.substringBetween(text,"Група", "Дисципліна");
        String sub =StringUtils.substringBetween(text,"Дисципліна", "Семестр");
        String sem =StringUtils.substringBetween(text,"Семестр", "Залікові");
        String contr =StringUtils.substringBetween(text,"контролю:", "Дата");
        String dat =StringUtils.substringBetween(text,"Дата", "р.");
        String teach =StringUtils.substringBetween(text,"р.", "Прізвище");
        String teachnamefull =StringUtils.substringBefore(teach,",");
        String teachzv =StringUtils.substringBetween(teach,",",",");
        String teachpos =StringUtils.substringAfterLast(teach,",");
        dat= dat.replaceAll(" ", "")
                .replaceAll("квітня","/04/")
                .replaceAll("«","")
                .replaceAll("»","");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        LocalDate dateTime = LocalDate.parse(dat, formatter);
        Date datefinal = Date.valueOf(dateTime);
        System.out.println(dateTime);
        edu = edu.replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        fac= fac.replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        year=year.replaceAll(" ", "")
                .replaceAll("_", "")
                .replaceAll("\n", "");
        int yearI=Integer.parseInt(year);
        grouup= grouup.replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        sub= sub.replaceAll("\n", "")
                .replaceAll("\r", "");
        sem=sem.replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("_", "")
                .replaceAll("\n", "");
        int semI=Integer.parseInt(sem);
        contr= contr.replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("_", "")
                .replaceAll("\n", "");
        teachnamefull= teachnamefull
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        teachnamefull= teachnamefull.replaceFirst(" ", "");
        String[] tech = teachnamefull.split(" ");
        System.out.println(Arrays.toString(tech));
        String tlast=tech[1];
        String tfirst = tech[2];
        String tSecond = tech[3];
        teachzv= teachzv
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        teachpos= teachpos
                .replaceAll("\r", "")
                .replaceAll("\n", "");

        System.out.println(text);
        System.out.println("edu\n"+edu);
        System.out.println("fac\n"+fac);
        System.out.println("year\n"+year);
        System.out.println("group\n"+grouup);
        System.out.println("sub\n"+sub);
        System.out.println("sem\n"+sem);
        System.out.println("contr\n"+contr);
        System.out.println("dat\n"+dat);
        System.out.println("teach\n"+teach);
        System.out.println("teach\n"+teachnamefull);
        System.out.println("teachlast\n"+tlast);
        System.out.println("teachfirst\n"+tfirst);
        System.out.println("teachseconf\n"+tSecond);

        System.out.println("teachzv\n"+teachzv);
        System.out.println("teachpos\n"+teachpos);

        String marks =StringUtils.substringBetween(text,"викладача","*");
        String end =StringUtils.substringBetween(text,"*","Декан");

        System.out.println("marks\n"+marks);
        System.out.println("end\n"+end);
        String ontest =StringUtils.substringBetween(end,"_", "_");
        ontest= ontest
                .replaceAll(" ", "");
        String absentf =StringUtils.substringBetween(end,"не з’явились", "Кількість");
        String absent =StringUtils.substringBetween(absentf,"_", "_");
        String notallowed =StringUtils.substringAfterLast(end,"заліку");
        notallowed= notallowed
                .replaceAll(" ", "")
                .replaceAll("_", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        System.out.println("ontest\n"+ontest);
        System.out.println("absent\n"+absent);
        System.out.println("notallowed\n"+notallowed);
        int ontestI=Integer.parseInt(ontest);
        int absentI=Integer.parseInt(absent);
        int notallowedI=Integer.parseInt(notallowed);

        String[] marksvidstr = marks.split(" "+Character.toString((char)13));

//        String help=marksvidstr[1];
//        String[] help1=help.split(" ");
////        for (int i=0; i<help.length(); i++) {
////            System.out.println((int) help.charAt(i));
////        }
//        System.out.println(Arrays.toString(help1));
//        System.out.println(help1.length);


        System.out.println(Arrays.toString(marksvidstr));
        System.out.println(marksvidstr.length);
        String[][] markar= new String[ontestI+1][12];
        for (int i =1; i<ontestI+1;i++) {
            for (int j = 0; j < 12; j++) {
                 String[] help3=marksvidstr[i].split(" ");
               // System.out.println(Arrays.toString(help3));
                markar[i][j]=help3[j];
                System.out.print(markar[i][j] + " ");
            }
            System.out.println();
        }
     //   System.out.println(Arrays.toString(markar));

        System.out.println("markar\n"+Arrays.toString(markar));
        String[] words = text.split("[ _,.]"+Character.toString((char)13));



        //object creation
        //subject
        Subject subjecthelp= new Subject(sub,edu,fac);
        if (getIdsIfExists.getSubjectId(subjecthelp)==0){
            insertStatements.insertSubject(subjecthelp);
        }
        subjecthelp.setId_subject(getIdsIfExists.getSubjectId(subjecthelp));
        //teaccher
        Teacher teacher= new Teacher(tfirst,tlast,tSecond,teachpos,teachzv,"academ_status xzzz");
        if (getIdsIfExists.getTeacherId(teacher)==0){
            insertStatements.insertTeacher(teacher);
        }
        teacher.setId_teacher(getIdsIfExists.getTeacherId(teacher));
        //groupst
        Group_st group_st = new Group_st(grouup,2077,semI,yearI,subjecthelp.getId_subject());
        if (getIdsIfExists.getGroupId(group_st)==0)
        {
            insertStatements.insertGroup(group_st);
        }
        group_st.setId_group(getIdsIfExists.getGroupId(group_st));

//        dataexam
        Data_exam data_exam = new Data_exam(ontestI,absentI,notallowedI,contr,datefinal,group_st.getId_group(),teacher.getId_teacher());
        if (getIdsIfExists.getDataExamId(data_exam)==0)
        {
            insertStatements.insertDataExam(data_exam);
        }
        data_exam.setId_data_exam(getIdsIfExists.getDataExamId(data_exam));
//        students
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Mark_vid> mark_vids = new ArrayList<>();
        for (int i = 0; i<ontestI;i++)
        {
            String stlastname = markar[i+1][1];
            String stfirstname = markar[i+1][2];
            String stsecondname = markar[i+1][3];
            String recordbook = markar[i+1][4]+markar[i+1][5]+markar[i+1][6];
       //     System.out.println(stlastname+stfirstname+stsecondname+recordbook);
            Student student = new Student(stlastname,stfirstname,stsecondname,recordbook);
            students.add(student);
            if(getIdsIfExists.getStudentId(student)==0)
            {
                insertStatements.insertStudent(student);
            }
            student.setStud_id(getIdsIfExists.getStudentId(student));
            String mark1 = markar[i+1][7];
            String mark2 = markar[i+1][8];
            String markraz = markar[i+1][9];
            String nats = markar[i+1][10];
            String ekts = markar[i+1][11];
            int mark1I=Integer.parseInt(mark1);
            int mark2I=Integer.parseInt(mark2);
            int markrazI=Integer.parseInt(markraz);
             Mark_vid mark_vid = new Mark_vid(mark1I,mark2I,markrazI,nats,ekts,student.getStud_id(),data_exam.getId_data_exam());
             mark_vids.add(mark_vid);
             if (getIdsIfExists.getMarkVid(mark_vid)==0)
             {
                 insertStatements.insertMarkVid(mark_vid);
             }
             mark_vid.setId_mark_vid(getIdsIfExists.getMarkVid(mark_vid));
        }

        document.close();
    }
}