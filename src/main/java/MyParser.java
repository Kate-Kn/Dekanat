import Entities.Data_exam;
import Entities.Mark_bih;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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

    public  MyParser(String path) throws IOException {
        this.path=path;
        File file = new File(path);
        PDDocument document = load(file);

        PDFTextStripper pdfStripper = new PDFTextStripper();

        String text = pdfStripper.getText(document);
        String edu=StringUtils.substringBetween(text,"рівень", "Факультет");

        String fac=StringUtils.substringBetween(text,"Факультет", "Рік");
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

        System.out.println(dateTime);
        edu = edu.replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        fac= fac.replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        grouup= grouup.replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "");
        sub= sub.replaceAll("\n", "")
                .replaceAll("\r", "");
        sem=sem.replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("_", "")
                .replaceAll("\n", "");
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
        String[][] markar= new String[marksvidstr.length-2][12];
        for (int i =1; i<marksvidstr.length-2;i++) {
            for (int j = 0; j < 12; j++) {
                String[] help3=marksvidstr[i].split(" ");
               // System.out.println(Arrays.toString(help3));
                markar[i][j]=help3[j];
            }
        }
     //   System.out.println(Arrays.toString(markar));

        System.out.println("markar\n"+Arrays.toString(markar));
        String[] words = text.split("[ _,.]"+Character.toString((char)13));



        //object creation


        document.close();
    }
}