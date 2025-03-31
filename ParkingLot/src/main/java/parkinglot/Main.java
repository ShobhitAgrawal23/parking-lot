package parkinglot;

import parkinglot.model.ParkStrategy;
import parkinglot.model.Ticket;
import parkinglot.model.VehicleType;
import parkinglot.service.Entrance;
import parkinglot.service.ParkingSpotManager;
import parkinglot.service.exception.SpotNotFoundException;
import parkinglot.service.spot.ParkingSpot;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ParkingSpotManager parkingSpotManager= ParkingSpotManager.getInstance();
        parkingSpotManager.setParkingSpots(List.of(
                new ParkingSpot(1, 0, VehicleType.FOUR_WHEELER),
                new ParkingSpot(2, 0, VehicleType.FOUR_WHEELER),
                new ParkingSpot(3, 0, VehicleType.FOUR_WHEELER),
                new ParkingSpot(4, 0, VehicleType.TWO_WHEELER),
                new ParkingSpot(5, 0, VehicleType.TWO_WHEELER),
                new ParkingSpot(6, 0, VehicleType.THREE_WHEELER)));
        parkingSpotManager.printAvailableParkingSpots();
        Entrance entrance= new Entrance();
        try {
            Ticket ticket = entrance.generateTicket(ParkStrategy.DEFAULT, VehicleType.FOUR_WHEELER, "XYZ");
            System.out.println(ticket);
            parkingSpotManager.printAvailableParkingSpots();
            parkingSpotManager.unPark(ticket.getParkingSpot());
        }catch (SpotNotFoundException e){
            System.out.println("Space not available for requested vehicle type");
        }
        parkingSpotManager.printAvailableParkingSpots();
    }
}
