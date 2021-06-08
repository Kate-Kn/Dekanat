package Entities;

import java.util.ArrayList;

public class Student {

    int stud_id;
    String last_name;
    String first_name;
    String father_name;//not ordinary
    String recordbook_no;//not ordinary
//with all
    public Student(int stud_id, String last_name, String first_name, String father_name, String recordbook_no) {
        this.stud_id = stud_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.father_name = father_name;
        this.recordbook_no = recordbook_no;
    }
    public Student(String last_name, String first_name, String father_name, String recordbook_no) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.father_name = father_name;
        this.recordbook_no = recordbook_no;
    }
//without recordbook_no
    public Student(int stud_id, String last_name, String first_name, String father_name) {
        this.stud_id = stud_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.father_name = father_name;
    }
//    public Student( String last_name, String first_name, String father_name, String recordbook_no) {
//        this.last_name = last_name;
//        this.first_name = first_name;
//        this.father_name = father_name;
//    }
////wihout father name
//    public Student(int stud_id, String last_name, String first_name, String recordbook_no) {
//        this.stud_id = stud_id;
//        this.last_name = last_name;
//        this.first_name = first_name;
//        this.recordbook_no = recordbook_no;
//    }
////without fathername and recordbook
//    public Student(int stud_id, String last_name, String first_name) {
//        this.stud_id = stud_id;
//        this.last_name = last_name;
//        this.first_name = first_name;
//    }

    public int getStud_id() {
        return stud_id;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String  getRecordbook_no() {
        return recordbook_no;
    }

    public void setRecordbook_no(String recordbook_no) {
        this.recordbook_no = recordbook_no;
    }
    public void validateManual() throws Exception {
        if(last_name == "" || first_name == "")
        {
            throw new Exception(Teacher.exeptions[0]);
        }
    }
    //processing after such mistakes must be conducted and saved to gave a list of them
    public ArrayList<String> validateAutofill() throws Exception
    {
        ArrayList<String> res = new ArrayList<>();
        return res;
    }
}
