package com.example.parkomania;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {
    @Id
    private int reservation_id;
    private LocalTime start;
    private LocalTime stop;
    private LocalDate date;
    private String time_summary;
    @ManyToOne
    @JoinColumn(name = "fk_vehicle_id")
    private Vehicle vehicle;
    @Enumerated(EnumType.STRING)
    private ReservationType type;

    public Reservation() {
    }

    public Reservation(int reservation_id, LocalTime start, LocalTime stop, LocalDate date, String time_summary, Vehicle vehicle, ReservationType type) {
        this.reservation_id = reservation_id;
        this.start = start;
        this.stop = stop;
        this.date = date;
        this.time_summary = time_summary;
        this.vehicle = vehicle;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservation_id=" + reservation_id +
                ", start=" + start +
                ", stop=" + stop +
                ", date=" + date +
                ", type=" + type +
                ", time_summary=" + time_summary +
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

    public String getTime_summary() {
        return time_summary;
    }

    public void setTime_summary(String time_summary) {
        this.time_summary = time_summary;
    }

    //    public Vehicle getVehicle() {
//        return vehicle;
//    }

    public String getVehicle(){
        return vehicle.getRegistration();
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
