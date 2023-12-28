package com.example.parkomania;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    private int user_id;
    private String phone_number;
    private String mail;

    public User() {
    }

    public User(int user_id, String phone_number, String mail, String password_hash, UserType type) {
        this.user_id = user_id;
        this.phone_number = phone_number;
        this.mail = mail;
        this.password_hash = password_hash;
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", phone_number='" + phone_number + '\'' +
                ", mail='" + mail + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", type=" + type +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    private String password_hash;
    @Enumerated(EnumType.STRING)
    private UserType type ;
}
