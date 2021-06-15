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
        //
        if (text.contains("ВІДОМІСТЬ")){
            String vidId=StringUtils.substringBetween(text,"№", "Освітній");
            String edu=StringUtils.substringBetween(text,"рівень", "Факультет");

        String fac=StringUtils.substringBetween(text,"Факультет", "Рік");
        String year=StringUtils.substringBetween(text,"навчання", "Група");

        String grouup =StringUtils.substringBetween(text,"Група", "Дисципліна");
        String sub =StringUtils.substringBetween(text,"Дисципліна", "Семестр");
        String sem =StringUtils.substringBetween(text,"Семестр", "Залікові");
            String credits =StringUtils.substringBetween(text,"бали", "Форма");
            String contr =StringUtils.substringBetween(text,"контролю:", "Дата");
        String dat =StringUtils.substringBetween(text,"Дата", "р.");
        String teach =StringUtils.substringBetween(text,"р.", "Прізвище");
        String teachnamefull =StringUtils.substringBefore(teach,",");

        String tshit = StringUtils.substringAfter(teach,",");
        String teachzv="";
        String teachpos="";
        if (tshit.contains(",")) {

             teachzv = StringUtils.substringBetween(teach, ",", ",");
             teachpos = StringUtils.substringAfterLast(teach, ",");
        }else
        {
            teachzv = StringUtils.substringAfterLast(teach, ",");
            teachpos ="";

        }
        dat= dat.replaceAll(" ", "")
                .replaceAll("квітня","/04/")
                .replaceAll("червня","/06/")
                .replaceAll("січня","/01/")
                .replaceAll("лютого","/02/")
                .replaceAll("березня","/03/")
                .replaceAll("червня","/06/")
                .replaceAll("липня","/07/")
                .replaceAll("серпня","/08/")
                .replaceAll("травня","/05/")
                .replaceAll("вересня","/09/")
                .replaceAll("жовтня","/10/")
                .replaceAll("листопада","/11/")
                .replaceAll("грудня","/12/")
                .replaceAll("«","")
                .replaceAll("»","");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        LocalDate dateTime = LocalDate.parse(dat, formatter);
        Date datefinal = Date.valueOf(dateTime);
        System.out.println(dateTime);
        edu = edu.replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "");

        vidId = vidId.replaceAll(" ", "")
                    .replaceAll("\r", "")
                    .replaceAll("\n", "");
        int vidIdInt=Integer.parseInt(vidId);
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
        credits=credits.replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("_", "")
                .replaceAll("\n", "");
        int creditsInt=Integer.parseInt(credits);
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
            String tlast = "";
            String tfirst = "";
            String tSecond ="";
            int i =tech.length;
             tlast = tech[0];
             tfirst = tech[1];
             if (i==3) {
                 tSecond = tech[3];
             }
        teachzv= teachzv
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        teachpos= teachpos
                .replaceAll("\r", "")
                .replaceAll("\n", "");

        System.out.println(text);
        System.out.println("vidId\n"+vidId);
        System.out.println("edu\n"+edu);
        System.out.println("fac\n"+fac);
        System.out.println("year\n"+year);
        System.out.println("group\n"+grouup);
        System.out.println("sub\n"+sub);
        System.out.println("sem\n"+sem);
        System.out.println("credits\n"+credits);
        System.out.println("contr\n"+contr);
        System.out.println("dat\n"+dat);
        System.out.println("teach\n"+teach);
        System.out.println("teach\n"+teachnamefull);
        System.out.println("teachlast\n"+tlast);
        System.out.println("teachfirst\n"+tfirst);
        System.out.println("teachseconf\n"+tSecond);

        System.out.println("teachzv\n"+teachzv);
        System.out.println("teachpos\n"+teachpos);
        String markshelp =StringUtils.substringBetween(text,"Підпис","*");

        String marks =StringUtils.substringAfter(markshelp,"1");
        marks="1 "+marks;

        String end =StringUtils.substringBetween(text,"*","Декан");

        System.out.println("marks\n"+marks);
        System.out.println("end\n"+end);

            String ontest = StringUtils.substringBetween(end, "заліку", "Кількість");
            ontest = ontest
                    .replaceAll(" ", "")
                    .replaceAll("\r", "")
                    .replaceAll("_", "")
                    .replaceAll("\n", "");
            String absentf = StringUtils.substringBetween(end, "не з’явились", "Кількість");
            String absent = StringUtils.substringAfter(absentf, "залік");
            String notallowed = StringUtils.substringAfterLast(end, "заліку");
            notallowed = notallowed
                    .replaceAll(" ", "")
                    .replaceAll("_", "")
                    .replaceAll("\r", "")
                    .replaceAll("\n", "");
            absent = absent
                    .replaceAll(" ", "")
                    .replaceAll("_", "")
                    .replaceAll("\r", "")
                    .replaceAll("\n", "");
            System.out.println("ontest\n" + ontest);
            System.out.println("absent\n" + absent);
            System.out.println("notallowed\n" + notallowed);
            int ontestI = Integer.parseInt(ontest);
            int absentI = Integer.parseInt(absent);
            int notallowedI = Integer.parseInt(notallowed);

            marks= marks.replaceAll("\\s+", " ")
                    .replaceAll("\r", "")
                    .replaceAll("\n", " ");
            marks= marks.replaceAll("Не зараховано", "Незараховано")
                    .replaceAll("Не відвідував", "Невідвідував")
                    .replaceAll("Не допущено", "Недопущено");
            System.out.println("marks\n"+marks);
            String[] marksvidstr = marks.split(" ");
            System.out.println("len\n"+marksvidstr.length);
            boolean fulltable = false;
            if (marksvidstr.length%12==0)
            {
             fulltable=true;
            }
            System.out.println(Arrays.toString(marksvidstr));
            System.out.println(marksvidstr.length);
            if (fulltable) {
                int amountofrows = marksvidstr.length/12;
                String[][] markar = new String[amountofrows][12];
                for (int ii = 0; ii < amountofrows; ii++) {
                    for (int j = 0; j < 12; j++) {
                        markar[ii][j] = marksvidstr[ii*12+j];
                        System.out.print(markar[ii][j] + "|");
                    }
                    System.out.println("end");
                }

                System.out.println("markar\n" + Arrays.toString(markar));
                String[] words = text.split("[ _,.]" + Character.toString((char) 13));


                //object creation
                //subject
                Subject subjecthelp = new Subject(sub, edu, fac);
                Teacher teacher = new Teacher(tfirst, tlast, tSecond, teachpos, teachzv, "academ_status xzzz");
                Group_st group_st = new Group_st(grouup, 2077, semI, yearI, subjecthelp.getId_subject());
                Data_exam data_exam = new Data_exam(vidIdInt, ontestI, absentI, notallowedI, contr, datefinal, group_st.getId_group(), teacher.getId_teacher());

                if (getIdsIfExists.getSubjectId(subjecthelp) == 0) {
                    insertStatements.insertSubject(subjecthelp);
                }
                subjecthelp.setId_subject(getIdsIfExists.getSubjectId(subjecthelp));
                //teaccher
                if (getIdsIfExists.getTeacherId(teacher) == 0) {
                    insertStatements.insertTeacher(teacher);
                }
                teacher.setId_teacher(getIdsIfExists.getTeacherId(teacher));
                //groupst
                 if (getIdsIfExists.getGroupId(group_st) == 0) {
                    insertStatements.insertGroup(group_st);
                }
                group_st.setId_group(getIdsIfExists.getGroupId(group_st));

//        dataexam
                if (getIdsIfExists.getDataExamId(data_exam) == 0) {
                    insertStatements.insertDataExam(data_exam);
                }
                data_exam.setId_data_exam(getIdsIfExists.getDataExamId(data_exam));
//        students
                ArrayList<Student> students = new ArrayList<>();
                ArrayList<Mark_vid> mark_vids = new ArrayList<>();
                for (int ii = 0; ii < ontestI; ii++) {
                    String stlastname = markar[ii + 1][1];
                    String stfirstname = markar[ii + 1][2];
                    String stsecondname = markar[ii + 1][3];
                    String recordbook = markar[ii + 1][4] + markar[ii + 1][5] + markar[ii + 1][6];
                    //     System.out.println(stlastname+stfirstname+stsecondname+recordbook);
                    Student student = new Student(stlastname, stfirstname, stsecondname, recordbook);
                    students.add(student);
                    if (getIdsIfExists.getStudentId(student) == 0) {
                        insertStatements.insertStudent(student);
                    }
                    student.setStud_id(getIdsIfExists.getStudentId(student));
                    String mark1 = markar[ii + 1][7];
                    String mark2 = markar[ii + 1][8];
                    String markraz = markar[ii + 1][9];
                    String nats = markar[ii + 1][10];
                    String ekts = markar[ii + 1][11];
                    int mark1I = Integer.parseInt(mark1);
                    int mark2I = Integer.parseInt(mark2);
                    int markrazI = Integer.parseInt(markraz);
                    Mark_vid mark_vid = new Mark_vid(mark1I, mark2I, markrazI, nats, ekts, student.getStud_id(), data_exam.getId_data_exam());
                    mark_vids.add(mark_vid);
                    if (getIdsIfExists.getMarkVid(mark_vid) == 0) {
                        insertStatements.insertMarkVid(mark_vid);
                    }
                    mark_vid.setId_mark_vid(getIdsIfExists.getMarkVid(mark_vid));
                }
            }
        }else
            //яко бігунець
            { String marksbih = StringUtils.substringBetween(text,"Підпис","*");
            marksbih=StringUtils.substringAfter(marksbih,"1");
            marksbih= "1 "+marksbih;
            marksbih = marksbih.replaceAll("\n","").replaceAll("\r","");
            String[] marksvidstr1 = marksbih.split(" ");

            System.out.println(Arrays.toString(marksvidstr1));
            System.out.println(marksvidstr1.length);
            String[][] markar= new String[1+1][12];
            for (int i =0; i<1+1;i++) {
                for (int j = 0; j < 12; j++) {
                    String[] help3=marksvidstr1[i].split(" ");
                    // System.out.println(Arrays.toString(help3));
//                    markar[i][j]=help3[j];
                 //   System.out.print(markar[i][j] + " ");
                }
                System.out.println();
            }


        }

        document.close();
    }
}