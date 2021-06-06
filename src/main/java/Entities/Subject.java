package Entities;

import java.util.ArrayList;

public class Subject {
    int id_subject;
    String name_subject;
    String edu_level;
    String faculty;

    public Subject(int id_subject, String name_subject, String edu_level, String faculty) {
        this.id_subject = id_subject;
        this.name_subject = name_subject;
        this.edu_level = edu_level;
        this.faculty = faculty;
    }
    public Subject(String name_subject, String edu_level, String faculty) {
        this.name_subject = name_subject;
        this.edu_level = edu_level;
        this.faculty = faculty;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getName_subject() {
        return name_subject;
    }

    public void setName_subject(String name_subject) {
        this.name_subject = name_subject;
    }

    public String getEdu_level() {
        return edu_level;
    }

    public void setEdu_level(String edu_level) {
        this.edu_level = edu_level;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    public void validateManual() throws Exception {
        if(edu_level == "")
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
