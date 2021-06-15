package Entities;

import java.util.ArrayList;

public class Group_st {
    ArrayList<String> groupErrors = new ArrayList();
    int id_group;
    String name_group;
    int year_study;
    int semester;
    int year_student;
    int id_subject;

    public Group_st(int id_group, String name_group, int year_study, int semester, int year_student, int id_subject) {
        this.id_group = id_group;
        this.name_group = name_group;
        this.year_study = year_study;
        this.semester = semester;
        this.year_student = year_student;
        this.id_subject = id_subject;
    }

    public Group_st(String name_group, int year_study, int semester, int year_student, int id_subject) {
        this.name_group = name_group;
        this.year_study = year_study;
        this.semester = semester;
        this.year_student = year_student;
        this.id_subject = id_subject;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public String getName_group() {
        return name_group;
    }

    public void setName_group(String name_group) {
        this.name_group = name_group;
    }

    public int getYear_study() {
        return year_study;
    }

    public void setYear_study(int year_study) {
        this.year_study = year_study;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getYear_student() {
        return year_student;
    }

    public void setYear_student(int year_student) {
        this.year_student = year_student;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public void validateManual() throws Exception {
        if (name_group.isEmpty()) {
            groupErrors.add("no subject name");
            throw new Exception("no subject name");
        }


    }
}
