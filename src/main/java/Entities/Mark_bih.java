package Entities;

import java.util.ArrayList;

public class Mark_bih {
    int id_mark_bih;
    int mark_sem;
    int mark_check;
    int mark_raz;
    String mark_nat;
    String mark_ekts;
    int id_mark_vid;
    int id_bih;

    public Mark_bih(int id_mark_bih, int mark_sem, int mark_check, int mark_raz, String mark_nat, String mark_ekts, int id_mark_vid, int id_bih) {
        this.id_mark_bih = id_mark_bih;
        this.mark_sem = mark_sem;
        this.mark_check = mark_check;
        this.mark_raz = mark_raz;
        this.mark_nat = mark_nat;
        this.mark_ekts = mark_ekts;
        this.id_mark_vid = id_mark_vid;
        this.id_bih = id_bih;
    }
    public Mark_bih(int mark_sem, int mark_check, int mark_raz, String mark_nat, String mark_ekts, int id_mark_vid, int id_bih) {
        this.mark_sem = mark_sem;
        this.mark_check = mark_check;
        this.mark_raz = mark_raz;
        this.mark_nat = mark_nat;
        this.mark_ekts = mark_ekts;
        this.id_mark_vid = id_mark_vid;
        this.id_bih = id_bih;
    }
    public int getId_mark_bih() {
        return id_mark_bih;
    }

    public void setId_mark_bih(int id_mark_bih) {
        this.id_mark_bih = id_mark_bih;
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

    public int getId_mark_vid() {
        return id_mark_vid;
    }

    public void setId_mark_vid(int id_mark_vid) {
        this.id_mark_vid = id_mark_vid;
    }

    public int getId_bih() {
        return id_bih;
    }

    public void setId_bih(int id_bih) {
        this.id_bih = id_bih;
    }
    public void validateManual() throws Exception {
        if(mark_sem == -1 || mark_check == -1 || mark_raz == -1|| mark_ekts.equals("")
                || mark_nat.equals(""))
        {
            throw new Exception(Teacher.exeptions[0] + "Not included mark");
        }
        if(mark_raz!=mark_check+mark_sem)
        {
            throw new Exception(Teacher.exeptions[0] + "Invalid sum");
        }
        if(mark_raz>=91&& !mark_ekts.equals("A"))
        {
            throw new Exception(Teacher.exeptions[0] + "Incorrect ekts");
        }
        if(mark_raz>=81 && mark_raz<91 && !mark_ekts.equals("B"))
        {
            throw new Exception(Teacher.exeptions[0] + "Incorrect ekts");
        }
        if(mark_raz>=71 && mark_raz<81 && !mark_ekts.equals("C"))
        {
            throw new Exception(Teacher.exeptions[0] + "Incorrect ekts");
        }
        if(mark_raz>=65 && mark_raz<71 && !mark_ekts.equals("D"))
        {
            throw new Exception(Teacher.exeptions[0] + "Incorrect ekts");
        }
        if(mark_raz>=60 && mark_raz<65 && !mark_ekts.equals("E"))
        {
            throw new Exception(Teacher.exeptions[0] + "Incorrect ekts");
        }
        if(mark_ekts.equals("F"))
        {
            throw new Exception(Teacher.exeptions[0] + "Can't contain F");
        }
    }
    //processing after such mistakes must be conducted and saved to gave a list of them
    public ArrayList<String> validateAutofill() throws Exception
    {
        ArrayList<String> res = new ArrayList<>();
        return res;
    }
}
