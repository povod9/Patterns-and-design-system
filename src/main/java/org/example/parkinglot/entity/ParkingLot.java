package org.example.parkinglot.entity;

import org.example.parkinglot.entity.enums.ParkingSlotStatus;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public final class ParkingLot {
    private static ParkingLot instance;
    private final List<ParkingSlot> parkingSlots;
    private final Scanner input = new Scanner(System.in);

    public ParkingLot(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }

    public static ParkingLot getInstance(List<ParkingSlot> parkingSlots){
        if(instance == null){
            instance = new ParkingLot(parkingSlots);
        }
        return instance;
    }

    public static void resetInstance(){
        instance = null;
    }

    public String park(Transport transport){
        var freeSpot = findFreeParkingSlot(transport);
        freeSpot.occupy(transport);
        return "Your car was successfully parked";
    }

    public List<ParkingSlot> getAllParkingSlots(){
        return parkingSlots;
    }

    public ParkingSlot getTakenSlotByTransportNumber(String transportNumber){
        return findTakenParkingSlot(transportNumber);
    }

    public String remove(String transportNumber){
        var toFreeParkingSpot = findTakenParkingSlot(transportNumber);
            toFreeParkingSpot.vacate();
            Duration totalAmountOfHours = timeDurationCounter(toFreeParkingSpot.arrivalTime, toFreeParkingSpot.setOffTime);
            BigDecimal totalPrice = payForTime(totalAmountOfHours);
            String message = "You were at the parking loot: " + totalAmountOfHours + ", pay before leaving";
            System.out.println(message + "\n You can zoom a card");
            var cardPay = input.nextBigDecimal();
            if(totalPrice.compareTo(cardPay) > 0){
                throw new PaymentException("Insufficient funds on the card");
            }
            return "Your car was successfully removed";
    }

    private Duration timeDurationCounter(LocalDateTime arrivalTime, LocalDateTime setOffTime){
        Duration duration = Duration.between(arrivalTime, setOffTime);
        if(duration.toMinutes() < Duration.ofMinutes(15).toMinutes()){
            return Duration.ZERO;
        }
        return duration.plus(Duration.ofHours(1));
    }

    private BigDecimal payForTime(Duration duration){
        return new BigDecimal(4*duration.toHoursPart());
    }

    private ParkingSlot findTakenParkingSlot(String transportNumber){
        return parkingSlots.stream()
                .filter(p -> p.getStatus() == ParkingSlotStatus.TAKEN)
                .filter(p -> p.getTransport().getTransportNumber().equals(transportNumber))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Couldn't find your car"));
    }

    private ParkingSlot findFreeParkingSlot(Transport transport){
        return parkingSlots.stream()
                .filter(p -> p.getStatus() == ParkingSlotStatus.FREE)
                .filter(p -> p.getType().isTransportFit(transport))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Couldn't find free slot"));
    }

}

