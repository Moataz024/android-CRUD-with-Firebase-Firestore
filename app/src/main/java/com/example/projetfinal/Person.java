package com.example.projetfinal;



public class Person {

    private String fullName;
    private String phone;
    private int age;


    public Person(String fullName, String phone, int age) {
        this.fullName = fullName;
        this.phone = phone;
        this.age = age;
    }

    public Person() {
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
