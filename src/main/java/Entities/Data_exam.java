package Entities;

import java.sql.Date;
import java.util.ArrayList;

public class Data_exam {
    int id_data_exam;
    int num_present;
    int num_absent;
    int num_not_allowed;
    String type_control;
    Date date_exam;
    int id_group;
    int id_teacher;

    public Data_exam(int id_data_exam, int num_present, int num_absent, int num_not_allowed, String type_control, Date date_exam, int id_group, int id_teacher) {
        this.id_data_exam = id_data_exam;
        this.num_present = num_present;
        this.num_absent = num_absent;
        this.num_not_allowed = num_not_allowed;
        this.type_control = type_control;
        this.date_exam = date_exam;
        this.id_group = id_group;
        this.id_teacher = id_teacher;
    }
    public Data_exam( int num_present, int num_absent, int num_not_allowed, String type_control, Date date_exam, int id_group, int id_teacher) {
        this.num_present = num_present;
        this.num_absent = num_absent;
        this.num_not_allowed = num_not_allowed;
        this.type_control = type_control;
        this.date_exam = date_exam;
        this.id_group = id_group;
        this.id_teacher = id_teacher;
    }

    public int getId_data_exam() {
        return id_data_exam;
    }

    public void setId_data_exam(int id_data_exam) {
        this.id_data_exam = id_data_exam;
    }

    public int getNum_present() {
        return num_present;
    }

    public void setNum_present(int num_present) {
        this.num_present = num_present;
    }

    public int getNum_absent() {
        return num_absent;
    }

    public void setNum_absent(int num_absent) {
        this.num_absent = num_absent;
    }

    public int getNum_not_allowed() {
        return num_not_allowed;
    }

    public void setNum_not_allowed(int num_not_allowed) {
        this.num_not_allowed = num_not_allowed;
    }

    public String getType_control() {
        return type_control;
    }

    public void setType_control(String type_control) {
        this.type_control = type_control;
    }

    public Date getDate_exam() {
        return date_exam;
    }

    public void setDate_exam(Date date_exam) {
        this.date_exam = date_exam;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }

    public void validateManual() throws Exception {
        if(num_absent == -1 || num_not_allowed==-1 || num_present==-1)
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
