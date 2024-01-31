package org.example.homeWork02;

public class Student {
    private String name;
    private String surName;

    public Student(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public String getSurName(){
        return surName;
    }
    public void print() {
        System.out.println("- Студент " + name + " " + surName);
    }

}
