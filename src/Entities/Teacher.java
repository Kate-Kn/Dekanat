package Entities;

public class Teacher {

    int id_teacher;
    String first_name;
    String last_name;
    String father_name;//not ordinary
    String position;//not ordinary
    String science_degree;//not ordinary
    String academ_status;//not ordinary
//with all
    public Teacher(int id_teacher, String first_name, String last_name, String father_name, String position, String science_degree, String academ_status) {
        this.id_teacher = id_teacher;
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
        this.position = position;
        this.science_degree = science_degree;
        this.academ_status = academ_status;
    }

//without 1 academ_status EROOOR!!!!!!!!!!!!
    public Teacher(int id_teacher, String first_name, String last_name, String father_name, String position, String science_degree) {
        this.id_teacher = id_teacher;
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
        this.position = position;
        this.science_degree = science_degree;
    }
    //without 2 academ_status ans science degree EROOOR!!!!!!!
    public Teacher(int id_teacher, String first_name, String last_name, String father_name, String position) {
        this.id_teacher = id_teacher;
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
        this.position = position;
    }
//without 3  academ_status science degree and positiobEROOOR!!!!!!!
    public Teacher(int id_teacher, String first_name, String last_name, String father_name) {
        this.id_teacher = id_teacher;
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
    }
    //without 3  academ_status science degree and position and father namebEROOOR!!!!!!!
    public Teacher(int id_teacher, String first_name, String last_name) {
        this.id_teacher = id_teacher;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getScience_degree() {
        return science_degree;
    }

    public void setScience_degree(String science_degree) {
        this.science_degree = science_degree;
    }

    public String getAcadem_status() {
        return academ_status;
    }

    public void setAcadem_status(String academ_status) {
        this.academ_status = academ_status;
    }
}
