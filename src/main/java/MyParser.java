import Entities.Data_exam;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.pdfbox.pdfparser.PDFParser.load;

public  class MyParser {

    String path;

    public  MyParser(String path) throws IOException {
        this.path=path;
        File file = new File(path);
        PDDocument document = load(file);

        PDFTextStripper pdfStripper = new PDFTextStripper();

        String text = pdfStripper.getText(document);

       // String s=StringUtils.substringBetween(text,"ВІДОМІСТЬ", "рівень");
        String edu=StringUtils.substringBetween(text,"рівень", "Факультет");

        String fac=StringUtils.substringBetween(text,"Факультет", "Рік");
        String grouup =StringUtils.substringBetween(text,"Група", "Дисципліна");
        String sub =StringUtils.substringBetween(text,"Дисципліна", "Семестр");
        String sem =StringUtils.substringBetween(text,"Семестр", "Залікові");
        String contr =StringUtils.substringBetween(text,"контролю:", "Дата");
        String dat =StringUtils.substringBetween(text,"Дата", "р.");
        String teach =StringUtils.substringBetween(text,"р.", "Прізвище");
        String teachname =StringUtils.substringBefore(teach,",");
        String teachzv =StringUtils.substringBetween(teach,",",",");
        String teachpos =StringUtils.substringAfterLast(teach,",");

        System.out.println(text);
        System.out.println("edu\n"+edu);
        System.out.println("fac\n"+fac);
        System.out.println("group\n"+grouup);
        System.out.println("sub\n"+sub);
        System.out.println("sem\n"+sem);
        System.out.println("contr\n"+contr);
        System.out.println("dat\n"+dat);
        System.out.println("teach\n"+teach);
        System.out.println("teach\n"+teachname);
        System.out.println("teach\n"+teachzv);
        System.out.println("teach\n"+teachpos);

        String marks =StringUtils.substringBetween(text,"викладача","*");
        String end =StringUtils.substringBetween(text,"*","Декан");

        System.out.println("marks\n"+marks);
        System.out.println("end\n"+end);


        String[] words = text.split("[ _,.]"+Character.toString((char)13));
        System.out.println(Arrays.toString(words));

//        String[] head = Arrays.copyOfRange(words, 3,11);
//        String[] body = Arrays.copyOfRange(words, 36,48);
//        String[] end = Arrays.copyOfRange(words, 49,53);
//        System.out.println("head\n"+Arrays.toString(head));
//        System.out.println("body\n"+Arrays.toString(body));
//        System.out.println("end\n"+Arrays.toString(end));
        String vidomist_id;
        String eduLevel;
        String year_study;
        String group_no;
        String subject_name;
        String semester;
        String control;
        String date;



        for (int i =0; i<words.length;i++)
        {
            if (words[i].equals("№")){
                vidomist_id=words[i+1];
                System.out.println("vidomist_id:  "+vidomist_id);
            }
            if (words[i].equals("рівень")){
                eduLevel=words[i+1];
                System.out.println("eduLevel:  "+eduLevel);
            }
            if (words[i].equals("навчання")){
                year_study=words[i+1];
                System.out.println("year_study:  "+year_study);
            }

            if (words[i].equals("Група")){
                group_no=words[i+1];
                System.out.println(i+ " group_no:  "+group_no);
            }
            if (words[i].equals("Дисципліна")){
                // dc. ufpde!!!!
                subject_name=words[i+3];
                System.out.println("subject_name:  "+subject_name);
            }
            if (words[i].equals("Семестр")){
                semester=words[i+1];
                System.out.println("semester:  "+semester);
            }
            if (words[i].equals("контролю:")){
                control=words[i+2];
                System.out.println("control:  "+control);
            }
            if (words[i].equals("Дата")){
                date=words[i+2];
                System.out.println("date:  "+date);
            }
            if (words[i].equals("р.")){
                date=words[i+2];
                System.out.println("date:  "+date);
            }
        }

        //System.out.println("\n\n\n\nTEXT\n"+text);

        document.close();
    }
}