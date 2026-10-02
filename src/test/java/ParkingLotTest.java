import org.example.parkinglot.entity.ParkingLot;
import org.example.parkinglot.entity.ParkingSlot;
import org.example.parkinglot.entity.Transport;
import org.example.parkinglot.entity.enums.ParkingSlotStatus;
import org.example.parkinglot.entity.enums.ParkingSlotType;
import org.example.parkinglot.entity.enums.TransportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {

    @BeforeEach
    void resetParkingLot(){
        ParkingLot.resetInstance();
    }

    @Test
    void shouldParkCarSuccessfully(){
        ParkingLot parkingLot = ParkingLot.getInstance(1,0);
        Transport car = new Transport("sad", TransportType.CAR);
        String result = parkingLot.park(car);

        assertEquals("Your car was successfully parked", result);
    }

    @Test
    void shouldParkToBiggerSlotIfSmallerIsOccupied(){
        ParkingLot parkingLot = ParkingLot.getInstance(0,1);
        Transport motorcycle = new Transport("sad", TransportType.MOTORCYCLE);
        String result = parkingLot.park(motorcycle);
        Optional<ParkingSlot> slot = parkingLot.getTakenSlotByTransportNumber(motorcycle.getTransportNumber());


        assertEquals("Your car was successfully parked", result);
        assertEquals(ParkingSlotType.MEDIUM, slot.get().getType());
    }

    @Test
    void shouldParkToSmallerSlotWithPriorityIfBiggerAvailable(){
        Transport motorcycle = new Transport("sad", TransportType.MOTORCYCLE);
        ParkingLot parkingLot = new ParkingLot(List.of(new ParkingSlot(null, ParkingSlotStatus.FREE, ParkingSlotType.MEDIUM),
                new ParkingSlot(null, ParkingSlotStatus.FREE, ParkingSlotType.SMALL)));

        String result = parkingLot.park(motorcycle);
        Optional<ParkingSlot> slot = parkingLot.getTakenSlotByTransportNumber(motorcycle.getTransportNumber());


        assertEquals("Your car was successfully parked", result);
        assertEquals(ParkingSlotType.SMALL, slot.get().getType());
    }

    @Test
    void shouldRemoveTransportSuccessfully(){
        ParkingLot parkingLot = ParkingLot.getInstance(1,0);
        Transport motorcycle = new Transport("sad", TransportType.MOTORCYCLE);
        String parkedResult = parkingLot.park(motorcycle);
        Optional<ParkingSlot> slot = parkingLot.getTakenSlotByTransportNumber(motorcycle.getTransportNumber());
        String removedResult = parkingLot.remove(motorcycle.getTransportNumber());

        assertEquals("Car was successfully removed from slot.\n" +
                "Total amount of hours: " + Duration.between(slot.get().getArrivalTime(), slot.get().getSetOffTime()), removedResult);
    }

    @Test
    void shouldReturnParkingSlotByTransportNumberSuccessfully(){
        ParkingLot parkingLot = ParkingLot.getInstance(1, 1);
        Transport car = new Transport("ss", TransportType.CAR);
        String result = parkingLot.park(car);
        Optional<ParkingSlot> slot = parkingLot.getTakenSlotByTransportNumber(car.getTransportNumber());

        assertEquals(car.getTransportNumber(), slot.get().getTransport().getTransportNumber());
        assertEquals(ParkingSlotType.MEDIUM, slot.get().getType());
    }
}
