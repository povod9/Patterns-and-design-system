package org.example.parkinglot.entity;

import org.example.parkinglot.entity.enums.ParkingSlotStatus;
import org.example.parkinglot.entity.enums.ParkingSlotType;

import java.util.ArrayList;

public class ParkingLotInitialization {
    private ArrayList<ParkingSlot> totalAmountOfParkingSlots = new ArrayList<>();

    public ArrayList<ParkingSlot> buildSlots(int small, int medium){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return totalAmountOfParkingSlots;
    }

    ParkingSlot createSmallParkingSlot(){
        return new ParkingSlot(
                null,
                ParkingSlotStatus.FREE,
                ParkingSlotType.SMALL
        );
    }

    ParkingSlot createMediumParkingSlot(){
        return new ParkingSlot(
                null,
                ParkingSlotStatus.FREE,
                ParkingSlotType.MEDIUM
        );
    }

    ParkingSlot createLargeParkingSlot(){
        return new ParkingSlot(
                null,
                ParkingSlotStatus.FREE,
                ParkingSlotType.LARGE
        );
    }
}

