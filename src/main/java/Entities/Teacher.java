package Entities;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Teacher {
    static String exeptions[] = {"manual","autofill"};
    int id_teacher;
    String first_name;
    String last_name;
    @Nullable
    String father_name;//not ordinary
    @Nullable
    String position;//not ordinary
    @Nullable
    String science_degree;//not ordinary
    @Nullable
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
    public Teacher( String first_name, String last_name, String father_name, String position, String science_degree, String academ_status) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
        this.position = position;
        this.science_degree = science_degree;
        this.academ_status = academ_status;
    }

////without 1 academ_status EROOOR!!!!!!!!!!!!
//    public Teacher(int id_teacher, String first_name, String last_name, String father_name, String position, String science_degree) {
//        this.id_teacher = id_teacher;
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.father_name = father_name;
//        this.position = position;
//        this.science_degree = science_degree;
//    }
//    //without 2 academ_status ans science degree EROOOR!!!!!!!
//    public Teacher(int id_teacher, String first_name, String last_name, String father_name, String position) {
//        this.id_teacher = id_teacher;
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.father_name = father_name;
//        this.position = position;
//    }
////without 3  academ_status science degree and positiobEROOOR!!!!!!!
//    public Teacher(int id_teacher, String first_name, String last_name, String father_name) {
//        this.id_teacher = id_teacher;
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.father_name = father_name;
//    }
//    //without 4  academ_status science degree and position and father namebEROOOR!!!!!!!
//    public Teacher(int id_teacher, String first_name, String last_name) {
//        this.id_teacher = id_teacher;
//        this.first_name = first_name;
//        this.last_name = last_name;
//    }

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

    public @Nullable String getFather_name() {
        return father_name;
    }

    public void setFather_name(@Nullable String father_name) {
        this.father_name = father_name;
    }

    public @Nullable String getPosition() {
        return position;
    }

    public void setPosition(@Nullable String position) {
        this.position = position;
    }

    public @Nullable String getScience_degree() {
        return science_degree;
    }

    public void setScience_degree(@Nullable String science_degree) {
        this.science_degree = science_degree;
    }

    public @Nullable String getAcadem_status() {
        return academ_status;
    }

    public void setAcadem_status(@Nullable String academ_status) {
        this.academ_status = academ_status;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id_teacher=" + id_teacher +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", father_name='" + father_name + '\'' +
                ", position='" + position + '\'' +
                ", science_degree='" + science_degree + '\'' +
                ", academ_status='" + academ_status + '\'' +
                '}';
    }
    //no processing after such mistakes
    public void validateManual() throws Exception {
        if(last_name == "" || first_name == "")
        {
            throw new Exception(exeptions[0]);
        }
    }
    //processing after such mistakes must be conducted and saved to gave a list of them
    public ArrayList<String> validateAutofill() throws Exception
    {
        ArrayList<String> res = new ArrayList<>();
        return res;
    }
}
