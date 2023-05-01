package com.example.mybvp.Model;

public class AttendanceModel
{
    private String name;
    private String uid;
    private String roll_no;
    private String Course;

    public AttendanceModel() {}

    public AttendanceModel(String name, String roll_no, String Course, String uid) {
        this.name = name;
        this.roll_no = roll_no;
        this.Course = Course;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        this.Course = course;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
