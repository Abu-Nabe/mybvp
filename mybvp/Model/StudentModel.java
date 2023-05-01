package com.example.mybvp.Model;

public class StudentModel {
    private int absents;
    private String date;
    private int presents;
    private String roll_number;
    private String student_id;
    private String subject_name;
    private String teacher_id;
    private String type;
    private String username;

    public StudentModel() {}

    public StudentModel(int absents, String date, int presents, String roll_number,
                           String student_id, String subject_name, String teacher_id,
                           String type, String username) {
        this.absents = absents;
        this.date = date;
        this.presents = presents;
        this.roll_number = roll_number;
        this.student_id = student_id;
        this.subject_name = subject_name;
        this.teacher_id = teacher_id;
        this.type = type;
        this.username = username;
    }

    public int getAbsents() {
        return absents;
    }

    public void setAbsents(int absents) {
        this.absents = absents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPresents() {
        return presents;
    }

    public void setPresents(int presents) {
        this.presents = presents;
    }

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
