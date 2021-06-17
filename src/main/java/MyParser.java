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

    public  MyParser(String path) throws Exception {
        this.path=path;
        File file = new File(path);
        PDDocument document = load(file);

        PDFTextStripper pdfStripper = new PDFTextStripper();

        String text = pdfStripper.getText(document);
        text= text.replaceAll("\n","")
                .replaceAll("\r","");
        //
        try {

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
                .replaceAll("\n","")
                .replaceAll("\r","")
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
                .replaceAll("д", "1")
                .replaceAll("\n", "");
        int semI=Integer.parseInt(sem);
        credits=credits.replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("_", "")
                .replaceAll("\n", "");
              //  .replaceAll(".", ",");
        double creditsInt=Double.parseDouble(credits);
        contr= contr.replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("_", "")
                .replaceAll("\n", "");
        teachnamefull= teachnamefull
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        teachnamefull= teachnamefull.replaceAll(" ", "");
        String[] tech = teachnamefull.split("(?=[A-Я])");
        System.out.println(Arrays.toString(tech));
            String tlast = "";
            String tfirst = "";
            String tSecond ="";
            int techfulllength =tech.length;
             tlast = tech[0];
             tfirst = tech[1];
             if (techfulllength==3) {
                 tSecond = tech[2];
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
                    .replaceAll("\r", " ")
                    .replaceAll("\n", " ")
                    .replaceAll("бп", "бп ")
                    .replaceAll("мп", "мп ")
                    .replaceAll("\\s+", " ");
            marks= marks.replaceAll("Не зараховано", "Незараховано")
                    .replaceAll("Не відвідував", "Невідвідував")
                    .replaceAll("Не допущений", "Недопущено");
            marks= marks.replaceAll("Незараховано","Незараховано ")
                    .replaceAll( "Невідвідував","Невідвідував ")
                    .replaceAll("Недопущений", "Недопущений")
                    .replaceAll("\\s+", " ")
                    .replaceAll("A", "A ")
                    .replaceAll("B", "B ")
                    .replaceAll("C", "C ")
                    .replaceAll("D", "D ")
                    .replaceAll("E", "E ")
                    .replaceAll("F", "F ")
                    .replaceAll("\\s+", " ");

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
//            if (fulltable) {
//                int amountofrows = marksvidstr.length/12;
//                String[][] markar = new String[amountofrows][12];
//                for (int ii = 0; ii < amountofrows; ii++) {
//                    for (int j = 0; j < 12; j++) {
//                        markar[ii][j] = marksvidstr[ii*12+j];
//                        System.out.print(markar[ii][j] + "|");
//                    }
//                    System.out.println("end");
//                }
//
//                System.out.println("markar\n" + Arrays.toString(markar));
//
//
//                //object creation
//                //subject
//                Subject subjecthelp = new Subject(sub, edu, fac);
//                Teacher teacher = new Teacher(tfirst, tlast, tSecond, teachpos, teachzv, "academ_status xzzz");
////                Group_st group_st = new Group_st(grouup, 2077, semI, yearI, subjecthelp.getId_subject());
////                Data_exam data_exam = new Data_exam(vidIdInt, ontestI, absentI, notallowedI, contr, datefinal, group_st.getId_group(), teacher.getId_teacher());
//
//                ArrayList<Student> students = new ArrayList<>();
//                ArrayList<Mark_vid> mark_vids = new ArrayList<>();
//                for (int ii = 0; ii < ontestI; ii++) {
//                    String stlastname = markar[ii ][1];
//                    String stfirstname = markar[ii ][2];
//                    String stsecondname = markar[ii ][3];
//                    String recordbook = markar[ii ][4] + markar[ii ][5] + markar[ii ][6];
////                         System.out.println(stlastname+stfirstname+stsecondname+recordbook);
//                    Student student = new Student(stlastname, stfirstname, stsecondname, recordbook);
//                    students.add(student);
//
//                    String mark1 = markar[ii ][7];
//                    String mark2 = markar[ii ][8];
//                    String markraz = markar[ii ][9];
//                    String nats = markar[ii ][10];
//                    String ekts = markar[ii ][11];
//                    int mark1I = Integer.parseInt(mark1);
//                    int mark2I = Integer.parseInt(mark2);
//                    int markrazI = Integer.parseInt(markraz);
//                    Mark_vid mark_vid = new Mark_vid(mark1I, mark2I, markrazI, nats, ekts, student.getStud_id(), -1);
//                    mark_vid.validateManual();
//                    mark_vids.add(mark_vid);
//
////
//                }
//                boolean caninsert=false;
//                //validation
//                caninsert=true;
////                Group_st group_st = new Group_st(grouup, 2077, semI, yearI, subjecthelp.getId_subject());
////                Data_exam data_exam = new Data_exam(vidIdInt, ontestI, absentI, notallowedI, contr, datefinal, group_st.getId_group(), teacher.getId_teacher());
//
//                //inserting
//                Data_exam data_exam= new Data_exam();
//                    if(caninsert) {
//                        if (getIdsIfExists.getSubjectId(subjecthelp) == 0) {
//                            insertStatements.insertSubject(subjecthelp);
//                        }
//                        subjecthelp.setId_subject(getIdsIfExists.getSubjectId(subjecthelp));
//                        //teaccher
//                        if (getIdsIfExists.getTeacherId(teacher) == 0) {
//                            insertStatements.insertTeacher(teacher);
//                        }
//                        teacher.setId_teacher(getIdsIfExists.getTeacherId(teacher));
//                        //groupst
//                        Group_st group_st = new Group_st(grouup, 2077, semI, yearI, subjecthelp.getId_subject());
//
//                        if (getIdsIfExists.getGroupId(group_st) == 0) {
//                            insertStatements.insertGroup(group_st);
//                        }
//                        group_st.setId_group(getIdsIfExists.getGroupId(group_st));
//                        data_exam = new Data_exam(vidIdInt, ontestI, absentI, notallowedI, contr, datefinal, group_st.getId_group(), teacher.getId_teacher());
//
//                    // dataexam
//                        if (getIdsIfExists.getDataExamId(data_exam) == 0) {
//                            insertStatements.insertDataExam(data_exam);
//                        }
//
//                       // data_exam.setId_data_exam(getIdsIfExists.getDataExamId(data_exam));
//
//                        System.out.println(sub.toString());
//                        System.out.println(teacher.toString());
//                        System.out.println(group_st.toString());
//                        System.out.println(data_exam.toString());
//                        //students and markvid
//                       for (int k =0 ; k<students.size(); k++)
//                       {
//                           Student student = students.get(k);
//                           Mark_vid mark_vid = mark_vids.get(k);
//                           if (getIdsIfExists.getStudentId(student) == 0) {
//                                insertStatements.insertStudent(student);
//                           }
//                           student.setStud_id(getIdsIfExists.getStudentId(student));
//                           mark_vid.setId_data_exam(data_exam.getId_data_exam());
//                           mark_vid.setStud_id(student.getStud_id());
//                           if (getIdsIfExists.getMarkVid(mark_vid) == 0) {
//                               insertStatements.insertMarkVid(mark_vid);
//                           }
//                           mark_vid.setId_mark_vid(getIdsIfExists.getMarkVid(mark_vid));
//
//                           System.out.println(student.toString()+"\t"+mark_vid.toString());
//                       }
//                    }
//            } else
            {
                ArrayList<String> num=new ArrayList<>();
                ArrayList<String> last=new ArrayList<>();
                ArrayList<String> first=new ArrayList<>();
                ArrayList<String> second=new ArrayList<>();
                ArrayList<String> recordi=new ArrayList<>();
                ArrayList<String> recordslesh=new ArrayList<>();
                ArrayList<String> recorded=new ArrayList<>();
                ArrayList<String> trym=new ArrayList<>();
                ArrayList<String> ekz=new ArrayList<>();
                ArrayList<String> razom=new ArrayList<>();
                ArrayList<String> national=new ArrayList<>();
                ArrayList<String> ektsmarktable=new ArrayList<>();
                //якщо не повна таблиця
                for (int ii = 0; ii<marksvidstr.length;ii++) {
                   num.add(marksvidstr[ii]);
                   ii++;
                    last.add(marksvidstr[ii]);
                    ii++;
                    first.add(marksvidstr[ii]);
                    ii++;
                    if (marksvidstr[ii].equals("І")) {
                        second.add("");
                        recordi.add("І");
                        ii++;
                        recordslesh.add(marksvidstr[ii]);
                        ii++;
                        recorded.add(marksvidstr[ii]);
                        ii++;

                    } else {
                        second.add(marksvidstr[ii]);
                        ii++;
                        if (marksvidstr[ii].equals("І")) {
                            recordi.add("І");
                            ii++;
                            recordslesh.add(marksvidstr[ii]);
                            ii++;
                            recorded.add(marksvidstr[ii]);
                            ii++;
                        } else {
                            recordi.add("");
                            recordslesh.add("");
                            recorded.add("");
                        }
                    }
                    if (marksvidstr[ii].codePointAt(0) >= 48 && marksvidstr[ii].codePointAt(0) <= 57) {
                        trym.add(marksvidstr[ii]);
                        ii++;
                    } else {
                        trym.add("0");
                       // ekz.add("0");
                      //  razom.add("0");
                    }
                    if (marksvidstr[ii].codePointAt(0) >= 48 && marksvidstr[ii].codePointAt(0) <= 57) {
                        ekz.add(marksvidstr[ii]); ii++;
                    }
                    else {
                        ekz.add("0");
                    //    razom.add("0");
                    }
                    if (marksvidstr[ii].codePointAt(0) >= 48 && marksvidstr[ii].codePointAt(0) <= 57) {
                        razom.add(marksvidstr[ii]); ii++;
                    }else {
                        razom.add("0");
                    }
                    national.add(marksvidstr[ii]); ii++;
                    ektsmarktable.add(marksvidstr[ii]);

                    System.out.println("ok");
                }

                //array creation

                String[][] markar= new String[first.size()][12];
                for (int i = 0; i<first.size(); i++)
                {
                    for(int j=0; j<12; j++)
                    {
                        if(j==0) {
                            markar[i][j] =num.get(i);
                        }
                        if(j==1) {
                            markar[i][j] =last.get(i);
                        }
                        if(j==2) {
                            markar[i][j] =first.get(i);
                        }
                        if(j==3) {
                            markar[i][j] =second.get(i);
                        }
                        if(j==4) {
                            markar[i][j] =recordi.get(i);
                        }
                        if(j==5) {
                            markar[i][j] =recordslesh.get(i);
                        }
                        if(j==6) {
                            markar[i][j] =recorded.get(i);
                        }
                        if(j==7) {
                            markar[i][j] =trym.get(i);
                        }
                        if(j==8) {
                            markar[i][j] =ekz.get(i);
                        }
                        if(j==9) {
                            markar[i][j] =razom.get(i);
                        }
                        if(j==10) {
                            markar[i][j] =national.get(i);
                        }
                        if(j==11) {
                            markar[i][j] =ektsmarktable.get(i);
                        }
                    }
                    System.out.println("arrau ok");
                }
                for (int i = 0; i<first.size(); i++)
                {
                    for(int j=0; j<12; j++) {
                        System.out.print(markar[i][j]+"|");
                    }
                    System.out.println("end");
                }
                //object creation
                //subject
                Subject subjecthelp = new Subject(sub, edu, fac);
                Teacher teacher = new Teacher(tfirst, tlast, tSecond, teachpos, teachzv, "academ_status xzzz");
//                Group_st group_st = new Group_st(grouup, 2077, semI, yearI, subjecthelp.getId_subject());
//                Data_exam data_exam = new Data_exam(vidIdInt, ontestI, absentI, notallowedI, contr, datefinal, group_st.getId_group(), teacher.getId_teacher());

                ArrayList<Student> students = new ArrayList<>();
                ArrayList<Mark_vid> mark_vids = new ArrayList<>();
                for (int ii = 0; ii < ontestI; ii++) {
                    String stlastname = markar[ii ][1];
                    String stfirstname = markar[ii ][2];
                    String stsecondname = markar[ii ][3];
                    String recordbook = markar[ii ][4] + markar[ii ][5] + markar[ii ][6];
//                         System.out.println(stlastname+stfirstname+stsecondname+recordbook);
                    Student student = new Student(stlastname, stfirstname, stsecondname, recordbook);
                    students.add(student);

                    String mark1 = markar[ii ][7];
                    String mark2 = markar[ii ][8];
                    String markraz = markar[ii ][9];
                    String nats = markar[ii ][10];
                    String ekts = markar[ii ][11];
                    int mark1I = Integer.parseInt(mark1);
                    int mark2I = Integer.parseInt(mark2);
                    int markrazI = Integer.parseInt(markraz);
                    Mark_vid mark_vid = new Mark_vid(mark1I, mark2I, markrazI, nats, ekts, student.getStud_id(), -1);
                    mark_vid.validateManual();
                    mark_vids.add(mark_vid);

//
                }
                boolean caninsert2=false;
                //validation
                caninsert2=true;
//                Group_st group_st = new Group_st(grouup, 2077, semI, yearI, subjecthelp.getId_subject());
//                Data_exam data_exam = new Data_exam(vidIdInt, ontestI, absentI, notallowedI, contr, datefinal, group_st.getId_group(), teacher.getId_teacher());

                //inserting
                Data_exam data_exam= new Data_exam();
                if(caninsert2) {
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
                    Group_st group_st = new Group_st(grouup, 2077, semI, yearI, subjecthelp.getId_subject());

                    if (getIdsIfExists.getGroupId(group_st) == 0) {
                        insertStatements.insertGroup(group_st);
                    }
                    group_st.setId_group(getIdsIfExists.getGroupId(group_st));
                    data_exam = new Data_exam(vidIdInt, ontestI, absentI, notallowedI, contr, datefinal, group_st.getId_group(), teacher.getId_teacher());

                    // dataexam
                    if (getIdsIfExists.getDataExamId(data_exam) == 0) {
                        insertStatements.insertDataExam(data_exam);
                    }

                    // data_exam.setId_data_exam(getIdsIfExists.getDataExamId(data_exam));

                    System.out.println(sub.toString());
                    System.out.println(teacher.toString());
                    System.out.println(group_st.toString());
                    System.out.println(data_exam.toString());
                    //students and markvid
                    for (int k =0 ; k<students.size(); k++)
                    {
                        Student student = students.get(k);
                        Mark_vid mark_vid = mark_vids.get(k);
                        if (getIdsIfExists.getStudentId(student) == 0) {
                            insertStatements.insertStudent(student);
                        }
                        student.setStud_id(getIdsIfExists.getStudentId(student));
                        mark_vid.setId_data_exam(data_exam.getId_data_exam());
                        mark_vid.setStud_id(student.getStud_id());
                        if (getIdsIfExists.getMarkVid(mark_vid) == 0) {
                            insertStatements.insertMarkVid(mark_vid);
                        }
                        mark_vid.setId_mark_vid(getIdsIfExists.getMarkVid(mark_vid));

                        System.out.println(student.toString()+"\t"+mark_vid.toString());
                    }
                }

            }
        }else
            //яко бігунець
            {
                String marksbih = StringUtils.substringBetween(text,"Підпис","*");
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
        }catch (Exception e){
            e.printStackTrace();
        }
        document.close();

}
}