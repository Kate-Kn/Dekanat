import Entities.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.apache.pdfbox.pdfparser.PDFParser.load;

public  class MyParser {

    String path;
    String error = "Нема помилок)";
    ArrayList<String> ordinaryStrings = new ArrayList<>();
    public  MyParser(String path) throws Exception {
        System.out.println("Start");
        this.path=path;
        File file = new File(path);
        PDDocument document = load(file);

        PDFTextStripper pdfStripper = new PDFTextStripper();

        String text = pdfStripper.getText(document);
        text= text.replaceAll("\n","")
                .replaceAll("\r","");

        try {

        if (text.contains("ВІДОМІСТЬ")){

            text= text.replaceAll("\\s+", " ")
                    .replaceAll("\r", "")
                    .replaceAll("\n", "");

            String vidId=StringUtils.substringBetween(text,"№", "Освітній");
            ordinaryStrings.add(vidId);
            String edu=StringUtils.substringBetween(text,"рівень", "Факультет");
            String fac=StringUtils.substringBetween(text,"Факультет", "Рік");
            if(edu.equals(null)) throw new Exception("нема факультету");
            String year=StringUtils.substringBetween(text,"навчання", "Група");
            String grouup =StringUtils.substringBetween(text,"Група", "Дисципліна");
            String sub =StringUtils.substringBetween(text,"Дисципліна", "Семестр");
            String sem =StringUtils.substringBetween(text,"Семестр", "Залікові");
            String credits =StringUtils.substringBetween(text,"бали", "Форма");
            String contr =StringUtils.substringBetween(text,"контролю:", "Дата");
            String dat =StringUtils.substringBetween(text,"Дата", "р.");
            String styduear = StringUtils.substringBetween(dat,"»", "р.");
//            styduear = styduear.replaceAll(" ","");
//            int styduearI=Integer.parseInt(styduear);
            String teach =StringUtils.substringBetween(text,"р.", "Прізвище");
            String teachnamefull =StringUtils.substringBefore(teach,",");
            String tshit = StringUtils.substringAfter(teach,",");
            String teachzv="";
            String teachpos="";
            ordinaryStrings.add(vidId);
            ordinaryStrings.add(edu);

            ordinaryStrings.add(fac);
            ordinaryStrings.add(year);
            ordinaryStrings.add(grouup);
            ordinaryStrings.add(sub);
            ordinaryStrings.add(sem);
            ordinaryStrings.add(credits);
            ordinaryStrings.add(contr);
            ordinaryStrings.add(dat);

            for (int l =0; l<ordinaryStrings.size(); l++)
            {
                if(ordinaryStrings.get(l).equals(""))
                {
                    throw new Exception("Помилка в шапчці");
                }
            }
            if (tshit.contains(",")) {

                teachpos = StringUtils.substringBetween(teach, ",", ",");
                teachzv = StringUtils.substringAfterLast(teach, ",");
            }else
            {
                teachpos = StringUtils.substringAfterLast(teach, ",");
                teachzv ="";
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
            styduear = StringUtils.substringAfterLast(dat,"/");
          //  styduear = styduear.replaceAll(" ","");
             int styduearI=Integer.parseInt(styduear);
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
                   // .replaceAll("д", "1")
                    .replaceAll("\n", "");
           // int semI=Integer.parseInt(sem);
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

                int amountOfrows = first.size();
                int nedop=0;
                int nevidvid=0;
                for (int i  = 0; i<amountOfrows;i++){
                    if (national.get(i).equals("Недопущено"))
                    {
                        nedop++;
                    }
                    if (national.get(i).equals("Невідвідував"))
                    {
                        nevidvid++;
                    }
                }
                if(amountOfrows-nevidvid-nedop!=ontestI)
                {
                    throw new Exception("Не збігається кількість людей у відомості");
                }
                if(nedop!=notallowedI)
                {
                    throw new Exception("Не збігається кількість недопущених");
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
//                    try {
                        mark_vid.validateManual();
//                    }catch (Exception e){
//                        e.printStackTrace();

//                        error = e.getMessage();
//                    }

                    mark_vids.add(mark_vid);

                }
                boolean caninsert2=false;
                //validation
                caninsert2=true;

                //inserting
                Data_exam data_exam= new Data_exam();
                boolean firstsub=false;
                if(caninsert2) {
                    if (getIdsIfExists.getSubjectId(subjecthelp) == 0) {
                        firstsub=true;
                        try{
                        insertStatements.insertSubject(subjecthelp);}
                        catch (Exception e){
                            throw new Exception("Не можна вписати предмет");
                        }
                    }
                    subjecthelp.setId_subject(getIdsIfExists.getSubjectId(subjecthelp));
                    //teaccher
                    boolean firstTeachettimeinsertion=false;
                    if (getIdsIfExists.getTeacherId(teacher) == 0) {
                        firstTeachettimeinsertion = true;
                        try{
                            insertStatements.insertTeacher(teacher);
                        }
                         catch (Exception e){
                            if(firstsub) deleteStatements.deleteSubject(subjecthelp);
                           throw new Exception("Не можна вписати вчителя");
                        }
                    }
                    teacher.setId_teacher(getIdsIfExists.getTeacherId(teacher));
                    //groupst
                    Group_st group_st = new Group_st(grouup, styduearI, sem, yearI, subjecthelp.getId_subject());
boolean firatGroup= false;
                    if (getIdsIfExists.getGroupId(group_st) == 0) {
                        try{
                            firatGroup=true;
                            insertStatements.insertGroup(group_st);
                        }
                         catch (Exception e){
                             if(firstsub) deleteStatements.deleteSubject(subjecthelp);
                             if (firstTeachettimeinsertion) deleteStatements.deleteTeacher(teacher);
                        throw new Exception("Не можна вписати групу");
                    }
                    }
                    group_st.setId_group(getIdsIfExists.getGroupId(group_st));
                    data_exam = new Data_exam(vidIdInt, ontestI, absentI, notallowedI, contr, datefinal, group_st.getId_group(), teacher.getId_teacher());
boolean firstdata= false;
                    // dataexam
                    if (getIdsIfExists.getDataExamId(data_exam) == 0) {
                        firstdata = true;
                        try {insertStatements.insertDataExam(data_exam);
                        }
                         catch (Exception e){
                             if(firstsub) deleteStatements.deleteSubject(subjecthelp);
                             if(firstTeachettimeinsertion) deleteStatements.deleteTeacher(teacher);
                             if(firatGroup ) deleteStatements.deleteGroup(group_st);
                        throw new Exception("Не можна вписати відомість");
                    }
                    }

                    System.out.println(sub.toString());
                    System.out.println(teacher.toString());
                    System.out.println(group_st.toString());
                    System.out.println(data_exam.toString());
                    //students and markvid
                    ArrayList<Boolean> bollst= new ArrayList<>();
                    for (int k =0 ; k<students.size(); k++)
                    {
                        bollst.add(false);
                        Student student = students.get(k);
                        Mark_vid mark_vid = mark_vids.get(k);
                        if (getIdsIfExists.getStudentId(student) == 0) {
                            bollst.set(k, true);
                           try { insertStatements.insertStudent(student);
                           }
                           catch (Exception e){
                               if(firstdata) deleteStatements.deleteVid(data_exam);
                               if(firstsub) deleteStatements.deleteSubject(subjecthelp);
                               if(firstTeachettimeinsertion) deleteStatements.deleteTeacher(teacher);
                               if(firatGroup ) deleteStatements.deleteGroup(group_st);
                               for (int d = 0; d<=k;d++)
                                {
                                    if(bollst.get(k))
                                    deleteStatements.deleteStudent(students.get(d));
                                }
                                throw new Exception("Не можна вписати якогось студента");
                        }
                        }
                        student.setStud_id(getIdsIfExists.getStudentId(student));
                        mark_vid.setId_data_exam(data_exam.getId_data_exam());
                        mark_vid.setStud_id(student.getStud_id());
                        if (getIdsIfExists.getMarkVid(mark_vid) == 0) {
                           try { insertStatements.insertMarkVid(mark_vid);
                        }
                           catch (Exception e){
                               if(firstsub) deleteStatements.deleteSubject(subjecthelp);
                               if(firstTeachettimeinsertion) deleteStatements.deleteTeacher(teacher);
                               if(firatGroup ) deleteStatements.deleteGroup(group_st);
                               for (int d = 0; d<=k;d++)
                               {
                                   if(bollst.get(k))
                                       deleteStatements.deleteStudent(students.get(d));
                               }
                        throw new Exception("Не можна вписати оцінки з відомості якогось студента");
                    }
                        }
                        mark_vid.setId_mark_vid(getIdsIfExists.getMarkVid(mark_vid));

                        System.out.println(student.toString()+"\t"+mark_vid.toString());
                    }
                }

            }
        }else
            //яко бігунець
            {
                bihunetsParser(text);
            }
        }catch (Exception e){
            e.printStackTrace();

            System.err.println("видаляю все до дідька!!!");
            error = e.getMessage();
            throw e;
        }
        document.close();
        System.err.println("Помилка "+error);
        System.out.println("Кінець ");
}
        private void bihunetsParser(String text) throws Exception {
try {


            String vidId=StringUtils.substringBetween(text,"№", "Освітній");
            String edu=StringUtils.substringBetween(text,"рівень", "Факультет");

            String fac=StringUtils.substringBetween(text,"Факультет", "Рік");
            String year=StringUtils.substringBetween(text,"навчання", "Група");

            String grouup =StringUtils.substringBetween(text,"Група", "Дисципліна");
            String sub =StringUtils.substringBetween(text,"Дисципліна", "Семестр");
            String sem =StringUtils.substringBetween(text,"Семестр", "Залікові");
            String credits =StringUtils.substringBetween(text,"бали", "Направлення");
            String oktill = StringUtils.substringBetween(text,"дійсне до", "Причина");

            String reason = StringUtils.substringBetween(text,"перенесення", "Форма");

            String contr =StringUtils.substringBetween(text,"контролю:", "Дата");
            String dat =StringUtils.substringBetween(text,"Дата", "р.");
            String teach =StringUtils.substringBetween(text,"р.", "Прізвище");
            String teachnamefull =StringUtils.substringBefore(teach,",");
    String styduear = StringUtils.substringBetween(dat,"»", "р.");


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
            oktill=oktill.replaceAll(" ", "")
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
            if(oktill.equals("")){
                oktill= "01/01/1000";
            }
            Date datefinal = Date.valueOf(dateTime);

            LocalDate dateTimeok = LocalDate.parse(oktill, formatter);
            Date oktilld = Date.valueOf(dateTimeok);

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
                 //   .replaceAll("д", "1")
                    .replaceAll("\n", "");
         //   int semI=Integer.parseInt(sem);
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
   // styduear = styduear.replaceAll(" ","");
    styduear = StringUtils.substringAfterLast(dat,"/");
    int styduearI=Integer.parseInt(styduear);
            System.out.println(text);
            System.out.println("vidId\n"+vidId);
            System.out.println("edu\n"+edu);
            System.out.println("fac\n"+fac);
            System.out.println("year\n"+year);
            System.out.println("group\n"+grouup);
            System.out.println("sub\n"+sub);
            System.out.println("sem\n"+sem);
            System.out.println("credits\n"+credits);
            System.out.println("oktill\n"+oktill);
            System.out.println("reason\n"+reason);
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

            System.out.println("marks\n"+marks);
            marks= marks.replaceAll("\\s+", " ")
                    .replaceAll("\r", " ")
                    .replaceAll("\n", " ")
                    .replaceAll("бп", "бп ")
                    .replaceAll("мп", "мп ")
                    .replaceAll("\\s+", " ");

            marks= marks
                    .replaceAll("\\s+", " ")
                    .replaceAll("A", "A ")
                    .replaceAll("B", "B ")
                    .replaceAll("C", "C ")
                    .replaceAll("D", "D ")
                    .replaceAll("E", "E ")
                    .replaceAll("\\s+", " ");
            String[] marksbihstr = marks.split(" ");
            System.out.println("len\n"+marksbihstr.length);

            System.out.println(Arrays.toString(marksbihstr));
            System.out.println(marksbihstr.length);

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
                for (int ii = 0; ii<marksbihstr.length;ii++) {
                    num.add(marksbihstr[ii]);
                    ii++;
                    last.add(marksbihstr[ii]);
                    ii++;
                    first.add(marksbihstr[ii]);
                    ii++;
                    if (marksbihstr[ii].equals("І")) {
                        second.add("");
                        recordi.add("І");
                        ii++;
                        recordslesh.add(marksbihstr[ii]);
                        ii++;
                        recorded.add(marksbihstr[ii]);
                        ii++;

                    } else {
                        second.add(marksbihstr[ii]);
                        ii++;
                        if (marksbihstr[ii].equals("І")) {
                            recordi.add("І");
                            ii++;
                            recordslesh.add(marksbihstr[ii]);
                            ii++;
                            recorded.add(marksbihstr[ii]);
                            ii++;
                        } else {
                            recordi.add("");
                            recordslesh.add("");
                            recorded.add("");
                        }
                    }
                    if (marksbihstr[ii].codePointAt(0) >= 48 && marksbihstr[ii].codePointAt(0) <= 57) {
                        trym.add(marksbihstr[ii]);
                        ii++;
                    } else {
                        trym.add("0");
                        // ekz.add("0");
                        //  razom.add("0");
                    }
                    if (marksbihstr[ii].codePointAt(0) >= 48 && marksbihstr[ii].codePointAt(0) <= 57) {
                        ekz.add(marksbihstr[ii]); ii++;
                    }
                    else {
                        ekz.add("0");
                        //    razom.add("0");
                    }
                    if (marksbihstr[ii].codePointAt(0) >= 48 && marksbihstr[ii].codePointAt(0) <= 57) {
                        razom.add(marksbihstr[ii]); ii++;
                    }else {
                        razom.add("0");
                    }
                    national.add(marksbihstr[ii]); ii++;
                    ektsmarktable.add(marksbihstr[ii]);

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
                subjecthelp.setId_subject(getIdsIfExists.getSubjectId(subjecthelp));
                if (getIdsIfExists.getSubjectId(subjecthelp) == 0) {
                    System.out.println("bad bihynets nsybject");
                    throw new Exception("Для такого предмету не здавали відомості");
                }
                Teacher teacher = new Teacher(tfirst, tlast, tSecond, teachpos, teachzv, "academ_status xzzz");
//
                ArrayList<Student> students = new ArrayList<>();
                ArrayList<Mark_bih> mark_bihs = new ArrayList<>();
                int rowsnum = first.size();
                for (int ii = 0; ii < rowsnum; ii++) {
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
                    System.out.println(getIdsIfExists.getMarkVidByStudent(student,subjecthelp));
                    Mark_bih mark_bih = new Mark_bih(mark1I, mark2I, markrazI, nats, ekts,
                             getIdsIfExists.getMarkVidByStudent(student,subjecthelp),0);
                    System.out.println(mark_bih.getId_mark_vid());
                    try {
                        mark_bih.validateManual();
                    }catch (Exception e)
                    {
                        throw new Exception(e.getMessage());
                    }
                    mark_bihs.add(mark_bih);
                    System.out.println(mark_bihs.get(ii).getId_mark_vid());
                }
                boolean caninsert2=false;
                //validation
                caninsert2=true;
                //inserting

                Bihunets bihunets = new Bihunets();

                if(caninsert2) {

                    subjecthelp.setId_subject(getIdsIfExists.getSubjectId(subjecthelp));
                    //teaccher
                    boolean firsttrcherinsertion = false;
                    if (getIdsIfExists.getTeacherId(teacher) == 0) {
                        firsttrcherinsertion=true;
                       try{ insertStatements.insertTeacher(teacher);
                    }
                           catch (Exception e){
                        throw new Exception("Не можна вписати вчителя з бігунця");
                    }
                    }
                    teacher.setId_teacher(getIdsIfExists.getTeacherId(teacher));
                    //groupst
                    Group_st group_st = new Group_st(grouup, styduearI, sem, yearI, subjecthelp.getId_subject());
boolean firstGrop=false;
                    if (getIdsIfExists.getGroupId(group_st) == 0) {
                        firstGrop=true;
                        try {insertStatements.insertGroup(group_st);
                    }
                           catch (Exception e){
if(firsttrcherinsertion)
                        deleteStatements.deleteTeacher(teacher);

                        throw new Exception("Не можна вписати групу з бігунця");
                    }
                    }
                    group_st.setId_group(getIdsIfExists.getGroupId(group_st));
                    //data_exam = new Data_exam(vidIdInt, ontestI, absentI, notallowedI, contr, datefinal, group_st.getId_group(), teacher.getId_teacher());
                    bihunets = new Bihunets(vidIdInt, datefinal, oktilld, reason, contr, teacher.getId_teacher());
boolean firstbih = false;
                    if (getIdsIfExists.getBihId(bihunets) == 0) {
                        firstbih = true;
                        try {
                            insertStatements.insertBihunets(bihunets);
                        } catch (Exception e) {
                            if (firsttrcherinsertion) deleteStatements.deleteTeacher(teacher);
                            if (firstGrop) deleteStatements.deleteGroup(group_st);

                            throw new Exception("Не можна вписати бігунець");
                        }
                    }

                    // data_exam.setId_data_exam(getIdsIfExists.getDataExamId(data_exam));

                    System.out.println(sub.toString());
                    System.out.println(teacher.toString());
                    System.out.println(group_st.toString());
                    System.out.println(bihunets.toString());
                    //students and markvid
                    ArrayList<Boolean> boolst = new ArrayList<>();
                    for (int k = 0; k < students.size(); k++) {
                        boolst.add(false);
                        Student student = students.get(k);
                        Mark_bih mark_bih = mark_bihs.get(k);
                        if (getIdsIfExists.getStudentId(student) == 0) {
                            throw new Exception("Є студент, який не здавав цей предмет");
                        }
                        student.setStud_id(getIdsIfExists.getStudentId(student));
                        mark_bih.setId_bih(bihunets.getId_bih());
                        if (getIdsIfExists.getMarkBih(mark_bih) == 0) {
                           try{ insertStatements.insertMarkBih(mark_bih);
                        }
                           catch (Exception e){
if(firstbih) deleteStatements.deleteBih(bihunets);
                        if(firsttrcherinsertion)    deleteStatements.deleteTeacher(teacher);
                           if(firstGrop) deleteStatements.deleteGroup(group_st);
                            for (int d = 0; d<=k;d++)
                            {
                                if(boolst.get(d))
                                deleteStatements.deleteStudent(students.get(d));
                            }
                            throw new Exception("Не можна вписати оцінки бігунця якогось студента");
                        }
                        }
                        mark_bih.setId_mark_bih(getIdsIfExists.getMarkBih(mark_bih));

                        System.out.println(student.toString() + "\t" + mark_bih.toString());
                    }
                }}
        }catch (Exception e)
        {
            e.printStackTrace();
            System.err.println("ВИДАЛЯБ!!!");
            error =e.getMessage();
            throw e;
        }
    }
}