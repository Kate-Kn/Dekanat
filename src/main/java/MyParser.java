import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.apache.pdfbox.pdfparser.PDFParser.load;

public  class MyParser {

    String path;

    public  MyParser(String path) throws IOException {
        this.path=path;
        File file = new File(path);
        PDDocument document = load(file);

        PDFTextStripper pdfStripper = new PDFTextStripper();

        String text = pdfStripper.getText(document);
        System.out.println(text);
        String[] words = text.split("[ _]");
        System.out.println(Arrays.toString(words));

        String vidomist_id;
        String eduLevel;
        String year_study;
        String group_no;
        String subject_name;
        String semester;
        String control;
        String date;
        // List<String> list = Arrays.asList(words);
        // List<String> bad = new ArrayList<>();
        // bad.add("s");
        // System.out.println(list.size());
        //   System.out.println("["+(int)list.get(8).charAt(0)+"]");
//        for (int i =0; i<list.size();i++)
//        {
//           // System.out.println(i + ". ."+list.get(i));
//           // list.removeAll(bad);
////            if (list.get(i).charAt(0)==13||list.get(i).){
////                list.set(i,"s");
////            }
//           // System.out.println(i + ". ."+list.get(i));
//
//        }
        // System.out.println(list.size());

        //   System.out.println("\n\nlist\n\n|"+list.get(10)+"|");
        //   System.out.println("\n\nlist2\n\n"+list);
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