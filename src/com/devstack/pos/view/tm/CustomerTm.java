package com.devstack.pos.view.tm;

import javafx.scene.control.Button;

public class CustomerTm {
    private int code;
    private String email;
    private String name;
    private String contact;
    private double salary;
    private Button btn;

    public CustomerTm() {
    }

    public CustomerTm(int code, String email, String name, String contact, double salary, Button btn) {
        this.code = code;
        this.email = email;
        this.name = name;
        this.contact = contact;
        this.salary = salary;
        this.btn = btn;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
