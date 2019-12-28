package com.example.newversion;

import java.util.HashMap;

public class AbsentsClass {

    String name="";
    String id="";
    String number="4";
    HashMap<String,Object> stringObjectHashMap;

    public AbsentsClass() {
        stringObjectHashMap=new HashMap<>();
    }

    public AbsentsClass(String name, String id, String number) {
        this.name = name;
        this.id = id;
    }


    public HashMap<String, Object> getStringObjectHashMap() {
        return stringObjectHashMap;
    }

    public void setStringObjectHashMap(HashMap<String, Object> stringObjectHashMap) {
        this.stringObjectHashMap = stringObjectHashMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setnumber(String number) {
        this.number = number;
    }
    public String getId() {
        return id;
    }
    public String getnumber() {
        return number;
    }
    public void setId(String id) {
        this.id = id;
    }
}
