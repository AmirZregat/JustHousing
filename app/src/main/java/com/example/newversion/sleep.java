package com.example.newversion;

public class sleep {
    String Student_Id="";
    String Back_date ;
    String Going_date ;
    String PersonName;
    String Person_phone_num;
    public sleep(String id,String bd , String gd , String pn , String pp)
    {
        Back_date = bd;
        Going_date = gd;
        PersonName = pn;
        Person_phone_num = pp;
        Student_Id = id;
    }
    public sleep()
    {

    }

    public String getBack_date() {
        return Back_date;
    }

    public void setBack_date(String back_date) {
        Back_date = back_date;
    }

    public String getGoing_date() {
        return Going_date;
    }

    public void setGoing_date(String going_date) {
        Going_date = going_date;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public String getPerson_phone_num() {
        return Person_phone_num;
    }

    public void setPerson_phone_num(String person_phone_num) {
        Person_phone_num = person_phone_num;
    }

    public String getStudent_Id() {
        return Student_Id;
    }

    public void setStudent_Id(String student_Id) {
        Student_Id = student_Id;
    }
}
