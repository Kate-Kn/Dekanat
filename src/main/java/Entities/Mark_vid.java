package Entities;

public class Mark_vid {

    int id_mark_vid;
    int mark_sem;
    int mark_check;
    int mark_raz;
    String mark_nat;
    String mark_ekts;
    int stud_id;
    int id_data_exam;

    public Mark_vid(int mark_sem, int mark_check, int mark_raz, String mark_nat, String mark_ekts, int stud_id, int id_data_exam) {
        this.mark_sem = mark_sem;
        this.mark_check = mark_check;
        this.mark_raz = mark_raz;
        this.mark_nat = mark_nat;
        this.mark_ekts = mark_ekts;
        this.stud_id = stud_id;
        this.id_data_exam = id_data_exam;
    }

    public int getId_mark_vid() {
        return id_mark_vid;
    }

    public void setId_mark_vid(int id_mark_vid) {
        this.id_mark_vid = id_mark_vid;
    }

    public int getMark_sem() {
        return mark_sem;
    }

    public void setMark_sem(int mark_sem) {
        this.mark_sem = mark_sem;
    }

    public int getMark_check() {
        return mark_check;
    }

    public void setMark_check(int mark_check) {
        this.mark_check = mark_check;
    }

    public int getMark_raz() {
        return mark_raz;
    }

    public void setMark_raz(int mark_raz) {
        this.mark_raz = mark_raz;
    }

    public String getMark_nat() {
        return mark_nat;
    }

    public void setMark_nat(String mark_nat) {
        this.mark_nat = mark_nat;
    }

    public String getMark_ekts() {
        return mark_ekts;
    }

    public void setMark_ekts(String mark_ekts) {
        this.mark_ekts = mark_ekts;
    }

    public int getStud_id() {
        return stud_id;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
    }

    public int getId_data_exam() {
        return id_data_exam;
    }

    public void setId_data_exam(int id_data_exam) {
        this.id_data_exam = id_data_exam;
    }
}
