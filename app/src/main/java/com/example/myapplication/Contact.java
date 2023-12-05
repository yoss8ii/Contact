package com.example.myapplication;

public class Contact {
    private int id;
    private String name;
    private String email;
    private byte[] photo; // byte array to store the photo

    // Constructors, getters, and setters


    public Contact (){

    }

    public Contact(int id, String name, String email, byte[] photo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
