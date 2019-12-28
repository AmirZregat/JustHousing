package com.example.newversion;

public class studentinfoo {

    String SRoom_Num="",Status="",Student_Num="", Name="",thisemail="",
            parent="",thisbirthdayplace="",thisnationality="",thispenality="",thisdateofadmission="";



    public studentinfoo() {

    }

    public studentinfoo(String SRoom_Num, String status, String student_Num, String name, String thisemail,
                        String parent, String thisbirthdayplace, String thisnationality, String thispenality,
                        String thisdateofadmission) {
        this.SRoom_Num = SRoom_Num;
        Status = status;
        Student_Num = student_Num;
        Name = name;
        this.thisemail = thisemail;
        this.parent = parent;
        this.thisbirthdayplace = thisbirthdayplace;
        this.thisnationality = thisnationality;
        this.thispenality = thispenality;
        this.thisdateofadmission = thisdateofadmission;
    }

    public String getSRoom_Num() {
        return SRoom_Num;
    }

    public void setSRoom_Num(String SRoom_Num) {
        this.SRoom_Num = SRoom_Num;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStudent_Num() {
        return Student_Num;
    }

    public void setStudent_Num(String student_Num) {
        Student_Num = student_Num;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getThisemail() {
        return thisemail;
    }

    public void setThisemail(String thisemail) {
        this.thisemail = thisemail;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getThisbirthdayplace() {
        return thisbirthdayplace;
    }

    public void setThisbirthdayplace(String thisbirthdayplace) {
        this.thisbirthdayplace = thisbirthdayplace;
    }

    public String getThisnationality() {
        return thisnationality;
    }

    public void setThisnationality(String thisnationality) {
        this.thisnationality = thisnationality;
    }

    public String getThispenality() {
        return thispenality;
    }

    public void setThispenality(String thispenality) {
        this.thispenality = thispenality;
    }

    public String getThisdateofadmission() {
        return thisdateofadmission;
    }

    public void setThisdateofadmission(String thisdateofadmission) {
        this.thisdateofadmission = thisdateofadmission;
    }
}
