package com.example.newversion;

public class Contain {
    String nameBuild, idRoom,studentNumber,text;

    public Contain(String nameBuild, String idRoom, String studentNumber) {
        this.nameBuild = nameBuild;
        this.idRoom = idRoom;
        this.studentNumber = studentNumber;
    }

    public String getNameBuild() {
        return nameBuild;
    }


    public String getIdRoom() {
        return idRoom;
    }

    public String getStudentNumber() {
        return studentNumber;
    }
}
