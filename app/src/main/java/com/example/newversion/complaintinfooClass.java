package com.example.newversion;

public class complaintinfooClass {
    String ComplaintText;
    String IdOfpersonWhoMadeComplaint;
    String Name;
    public complaintinfooClass() {
        ComplaintText = "";
        IdOfpersonWhoMadeComplaint = "";
        Name = "";
    }

    public String getComplaintText() {
        return ComplaintText;
    }

    public void setComplaintText(String complaintText) {
        ComplaintText = complaintText;
    }

    public String getIdOfpersonWhoMadeComplaint() {
        return IdOfpersonWhoMadeComplaint;
    }

    public void setIdOfpersonWhoMadeComplaint(String idOfpersonWhoMadeComplaint) {
        IdOfpersonWhoMadeComplaint = idOfpersonWhoMadeComplaint;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
