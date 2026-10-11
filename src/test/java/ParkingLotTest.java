import org.example.parkinglot.entity.ParkingLot;
import org.example.parkinglot.entity.ParkingSlot;
import org.example.parkinglot.entity.Transport;
import org.example.parkinglot.entity.enums.ParkingSlotStatus;
import org.example.parkinglot.entity.enums.ParkingSlotType;
import org.example.parkinglot.entity.enums.TransportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        List<ParkingSlot> parkingSlotList = List.of(createMediumParkingSlot());
        ParkingLot parkingLot = ParkingLot.getInstance(parkingSlotList);
        Transport car = new Transport("sad", TransportType.CAR);
        String result = parkingLot.park(car);

        assertEquals("Your car was successfully parked", result);
    }

    @Test
    void shouldParkToBiggerSlotIfSmallerIsOccupied(){
        List<ParkingSlot> parkingSlotList = List.of(createSmallParkingSlot(), createMediumParkingSlot());
        ParkingLot parkingLot = ParkingLot.getInstance(parkingSlotList);
        Transport motorcycleToSmallSlot = createMotorcycle();
        Transport motorcycleToMediumSlot = new Transport("1",TransportType.MOTORCYCLE);
        String resultSmallSlot = parkingLot.park(motorcycleToSmallSlot);
        String resultMediumSlot = parkingLot.park(motorcycleToMediumSlot);
        Optional<ParkingSlot> slotSmall = Optional.of(parkingLot.getTakenSlotByTransportNumber(motorcycleToSmallSlot.getTransportNumber()));
        Optional<ParkingSlot> slotMedium = Optional.of(parkingLot.getTakenSlotByTransportNumber(motorcycleToMediumSlot.getTransportNumber()));

        assertEquals("Your car was successfully parked", resultSmallSlot);
        assertEquals(ParkingSlotType.SMALL, slotSmall.get().getType());

        assertEquals("Your car was successfully parked", resultMediumSlot);
        assertEquals(ParkingSlotType.MEDIUM, slotMedium.get().getType());
    }

    @Test
    void shouldParkToSmallerSlotWithPriorityIfBiggerAvailable(){
        Transport motorcycle = createMotorcycle();
        List<ParkingSlot> parkingSlotList = List.of(createSmallParkingSlot(),createMediumParkingSlot(),createLargeParkingSlot());
        ParkingLot parkingLot = ParkingLot.getInstance(parkingSlotList);

        String result = parkingLot.park(motorcycle);
        Optional<ParkingSlot> slot = Optional.of(parkingLot.getTakenSlotByTransportNumber(motorcycle.getTransportNumber()));


        assertEquals("Your car was successfully parked", result);
        assertEquals(ParkingSlotType.SMALL, slot.get().getType());
    }

    @Test
    void shouldRemoveTransportSuccessfully(){
        List<ParkingSlot> parkingSlotList = List.of(createSmallParkingSlot(), createMediumParkingSlot(), createLargeParkingSlot());
        ParkingLot parkingLot = ParkingLot.getInstance(parkingSlotList);
        Transport motorcycle = createMotorcycle();

        String parkedResult = parkingLot.park(motorcycle);
        assertEquals("Your car was successfully parked", parkedResult);
        assertEquals(ParkingSlotType.SMALL, parkingLot.getTakenSlotByTransportNumber(motorcycle.getTransportNumber()).getType());
        assertEquals(ParkingSlotStatus.TAKEN, parkingLot.getTakenSlotByTransportNumber(motorcycle.getTransportNumber()).getStatus());

        Optional<ParkingSlot> slot = Optional.of(parkingLot.getTakenSlotByTransportNumber(motorcycle.getTransportNumber()));
        String removedResult = parkingLot.remove(motorcycle.getTransportNumber());

        assertEquals("Your car was successfully removed", removedResult);
    }

    @Test
    void shouldReturnParkingSlotByTransportNumberSuccessfully(){
        List<ParkingSlot> parkingSlotList = List.of(createSmallParkingSlot(),createMediumParkingSlot(),createLargeParkingSlot());
        ParkingLot parkingLot = ParkingLot.getInstance(parkingSlotList);
        Transport car = createCar();
        String result = parkingLot.park(car);
        Optional<ParkingSlot> slot = Optional.of(parkingLot.getTakenSlotByTransportNumber(car.getTransportNumber()));

        assertEquals("Your car was successfully parked", result);
        assertEquals(car.getTransportNumber(), slot.get().getTransport().getTransportNumber());
        assertEquals(ParkingSlotType.MEDIUM, slot.get().getType());
    }

    private ParkingSlot createSmallParkingSlot(){
        return new ParkingSlot(null, ParkingSlotStatus.FREE, ParkingSlotType.SMALL);
    }

    private ParkingSlot createMediumParkingSlot(){
        return new ParkingSlot(null, ParkingSlotStatus.FREE, ParkingSlotType.MEDIUM);
    }

    private ParkingSlot createLargeParkingSlot(){
        return new ParkingSlot(null, ParkingSlotStatus.FREE, ParkingSlotType.LARGE);
    }

    private Transport createCar(){
        return new Transport("123", TransportType.CAR);
    }

    private Transport createMotorcycle(){
        return new Transport("123", TransportType.MOTORCYCLE);
    }

    private Transport createTruck(){
        return new Transport("123", TransportType.TRUCK);
    }


}
