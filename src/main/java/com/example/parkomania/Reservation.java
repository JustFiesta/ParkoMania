package com.example.parkomania;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    private int reservation_id;
    private LocalTime start;
    private LocalTime stop;
    private LocalDate date;
    private ReservationType type;
    private int parking_place;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Reservation() {
    }

    public Reservation(int reservation_id, LocalTime start, LocalTime stop, LocalDate date, ReservationType type, int parking_place, Vehicle vehicle) {
        this.reservation_id = reservation_id;
        this.start = start;
        this.stop = stop;
        this.date = date;
        this.type = type;
        this.parking_place = parking_place;
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservation_id=" + reservation_id +
                ", start=" + start +
                ", stop=" + stop +
                ", date=" + date +
                ", type=" + type +
                ", parking_place=" + parking_place +
                ", vehicle=" + vehicle +
                '}';
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getStop() {
        return stop;
    }

    public void setStop(LocalTime stop) {
        this.stop = stop;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ReservationType getType() {
        return type;
    }

    public void setType(ReservationType type) {
        this.type = type;
    }

    public int getParking_place() {
        return parking_place;
    }

    public void setParking_place(int parking_place) {
        this.parking_place = parking_place;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
