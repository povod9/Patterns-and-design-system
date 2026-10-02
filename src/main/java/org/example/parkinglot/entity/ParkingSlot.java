package org.example.parkinglot.entity;

import org.example.parkinglot.entity.enums.ParkingSlotStatus;
import org.example.parkinglot.entity.enums.ParkingSlotType;

import java.time.LocalDateTime;
import java.util.Objects;

public class ParkingSlot {
    Transport transport;
    ParkingSlotStatus status;
    ParkingSlotType type;
    LocalDateTime arrivalTime;
    LocalDateTime setOffTime;

    public ParkingSlot(Transport transport, ParkingSlotStatus status, ParkingSlotType type) {
        this.transport = transport;
        this.status = status;
        this.type = type;
    }

    public void occupy(Transport transport) {
        this.transport = transport;
        this.status = ParkingSlotStatus.TAKEN;
        this.arrivalTime = LocalDateTime.now();
    }

    public void vacate() {
        this.transport = null;
        this.status = ParkingSlotStatus.FREE;
        this.setOffTime = LocalDateTime.now();
    }

    public Transport getTransport() {
        return transport;
    }

    public ParkingSlotType getType() {
        return type;
    }

    public ParkingSlotStatus getStatus() {
        return status;
    }

    public LocalDateTime getArrivalTime(){
        return arrivalTime;
    }

    public LocalDateTime getSetOffTime(){
        return setOffTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return Objects.equals(transport, that.transport) && status == that.status && type == that.type && Objects.equals(arrivalTime, that.arrivalTime) && Objects.equals(setOffTime, that.setOffTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transport, status, type, arrivalTime, setOffTime);
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "transport=" + transport +
                ", status=" + status +
                ", type=" + type +
                ", arrivalTime=" + arrivalTime +
                ", setOffTime=" + setOffTime +
                '}' + "\n";
    }
}

