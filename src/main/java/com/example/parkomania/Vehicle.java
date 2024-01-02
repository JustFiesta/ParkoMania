package com.example.parkomania;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    private int vehicle_id;
    private String registration;

    public Vehicle() {
    }

    public Vehicle(int vehicle_id, String registration, User user) {
        this.vehicle_id = vehicle_id;
        this.registration = registration;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicle_id=" + vehicle_id +
                ", registration='" + registration + '\'' +
                ", user=" + user +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}