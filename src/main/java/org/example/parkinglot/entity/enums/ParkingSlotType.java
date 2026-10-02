package org.example.parkinglot.entity.enums;

import org.example.parkinglot.entity.Transport;

import java.util.List;

public enum ParkingSlotType {
    SMALL(List.of(TransportType.MOTORCYCLE)),
    MEDIUM(List.of(TransportType.MOTORCYCLE,TransportType.CAR)),
    LARGE(List.of(TransportType.MOTORCYCLE, TransportType.CAR, TransportType.TRUCK));

    List<TransportType> supportedTransportTypes;

    ParkingSlotType(List<TransportType> supportedTransportTypes) {
        this.supportedTransportTypes = supportedTransportTypes;
    }

    public boolean isTransportFit(Transport transport){
        return supportedTransportTypes.contains(transport.getTransportType());
    }
}
