package Entities;

import java.sql.Date;

public class Bihunets {
    int id_bih;
    Date date;
    Date ok_till; //not ordinary
    String reason;
    String control;
    int id_teacher;
//with all parametrs
    public Bihunets(int id_bih, Date date, Date ok_till, String reason, String control, int id_teacher) {
        this.id_bih = id_bih;
        this.date = date;
        this.ok_till = ok_till;
        this.reason = reason;
        this.control = control;
        this.id_teacher = id_teacher;
    }
//without till_ok parametr (it is neobovazkovyy)
    public Bihunets(int id_bih, Date date, String reason, String control, int id_teacher) {
        this.id_bih = id_bih;
        this.date = date;
        this.reason = reason;
        this.control = control;
        this.id_teacher = id_teacher;
    }

    public int getId_bih() {
        return id_bih;
    }

    public void setId_bih(int id_bih) {
        this.id_bih = id_bih;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getOk_till() {
        return ok_till;
    }

    public void setOk_till(Date ok_till) {
        this.ok_till = ok_till;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }
}
