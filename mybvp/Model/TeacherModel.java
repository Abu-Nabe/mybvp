package com.example.mybvp.Model;

public class TeacherModel {
    private String classname;
    private String mail;
    private String name;
    private String phone;
    private String subject;
    private String id;

    public TeacherModel() {
        // Default constructor required for calls to DataSnapshot.getValue(TeacherModel.class)
    }

    public TeacherModel(String classname, String mail, String name, String phone, String subject, String id) {
        this.classname = classname;
        this.mail = mail;
        this.name = name;
        this.phone = phone;
        this.subject = subject;
        this.id = id;
    }

    public String getClassname() {
        return this.classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
