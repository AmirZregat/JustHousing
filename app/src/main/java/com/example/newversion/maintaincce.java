package com.example.newversion;

public class maintaincce {

    String Room_num="";
    String Student_Id="";
    String text="";

    public maintaincce(String room_num, String student_Id, String text) {
        Room_num = room_num;
        Student_Id = student_Id;
        this.text = text;
    }

    public maintaincce() {
    }

    public String getRoom_num() {
        return Room_num;
    }

    public void setRoom_num(String room_num) {
        Room_num = room_num;
    }

    public String getStudent_Id() {
        return Student_Id;
    }

    public void setStudent_Id(String student_Id) {
        Student_Id = student_Id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
