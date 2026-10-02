package org.example.parkinglot.entity;

import org.example.parkinglot.entity.enums.TransportType;

import java.util.Objects;

public class Transport {
    String transportNumber;
    TransportType transportType;

    public Transport(String transportNumber, TransportType transportType) {
        this.transportNumber = transportNumber;
        this.transportType = transportType;
    }

    public String getTransportNumber() {
        return transportNumber;
    }

    public void setTransportNumber(String transportNumber) {
        this.transportNumber = transportNumber;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "transportNumber='" + transportNumber + '\'' +
                ", transportType=" + transportType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return Objects.equals(transportNumber, transport.transportNumber) && transportType == transport.transportType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transportNumber, transportType);
    }
}
