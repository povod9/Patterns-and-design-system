package org.example.parkinglot.entity;

import org.example.parkinglot.entity.enums.ParkingSlotStatus;
import org.example.parkinglot.entity.enums.ParkingSlotType;
import org.example.parkinglot.entity.enums.TransportType;

import java.util.List;

public class ParkingSlotMain {
    static void main() {
        ParkingLot parkingLot = ParkingLot.getInstance(List.of(new ParkingSlot(null, ParkingSlotStatus.FREE, ParkingSlotType.SMALL),
                new ParkingSlot(null, ParkingSlotStatus.FREE, ParkingSlotType.SMALL),
                new ParkingSlot(null, ParkingSlotStatus.FREE, ParkingSlotType.SMALL),
                new ParkingSlot(null, ParkingSlotStatus.FREE, ParkingSlotType.SMALL),
                new ParkingSlot(null, ParkingSlotStatus.FREE, ParkingSlotType.MEDIUM),
                new ParkingSlot(null, ParkingSlotStatus.FREE, ParkingSlotType.MEDIUM),
                new ParkingSlot(null, ParkingSlotStatus.FREE, ParkingSlotType.LARGE)));
        Transport car = new Transport("1", TransportType.CAR);
        Transport motorcycle = new Transport("sdawdawd", TransportType.MOTORCYCLE);

        System.out.println(parkingLot.park(car));
        System.out.println(parkingLot.getAllParkingSlots());

        System.out.println(parkingLot.getTakenSlotByTransportNumber("1"));
        System.out.println(parkingLot.remove("1"));
    }
}
