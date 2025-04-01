package parkinglot;

import parkinglot.model.ParkStrategy;
import parkinglot.model.PayStrategy;
import parkinglot.model.Ticket;
import parkinglot.model.VehicleType;
import parkinglot.service.Entrance;
import parkinglot.service.Exit;
import parkinglot.service.ParkingSpotManager;
import parkinglot.service.exception.SpotNotFoundException;
import parkinglot.service.spot.ParkingSpot;


import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ParkingSpotManager parkingSpotManager= ParkingSpotManager.getInstance();
        addParkingSpots(parkingSpotManager);
        parkingSpotManager.printAvailableParkingSpots();
        Entrance entrance= new Entrance(1);
        Ticket ticket1=null;
        try {
            ticket1 = entrance.generateTicket(ParkStrategy.NEAREST_TO_EXIT, VehicleType.THREE_WHEELER, "XYZ");
            System.out.println("ticket booked. "+"ParkingSpotId:"+ticket1.getParkingSpot().getId()+" floorNumber:"+ticket1.getParkingSpot().getFloorNumber());
            parkingSpotManager.printAvailableParkingSpots();
        }catch (SpotNotFoundException e){
            System.out.println("Space not available for requested vehicle type");
        }

        Entrance entrance2= new Entrance(2);
        Ticket ticket2=null;
        try {
            ticket2 = entrance2.generateTicket(ParkStrategy.NEAREST_TO_EXIT, VehicleType.THREE_WHEELER, "XYZ");
            System.out.println("ticket booked. "+"ParkingSpotId:"+ticket2.getParkingSpot().getId()+" floorNumber:"+ticket1.getParkingSpot().getFloorNumber());
            parkingSpotManager.printAvailableParkingSpots();
        }catch (SpotNotFoundException e){
            System.out.println("Space not available for requested vehicle type");
        }

        Entrance entrance3= new Entrance(3);
        Ticket ticket3=null;
        try {
            ticket3 = entrance3.generateTicket(ParkStrategy.NEAREST_TO_EXIT, VehicleType.THREE_WHEELER, "XYZ");
            System.out.println("ticket booked. "+"ParkingSpotId:"+ticket3.getParkingSpot().getId()+" floorNumber:"+ticket3.getParkingSpot().getFloorNumber());
            parkingSpotManager.printAvailableParkingSpots();
        }catch (SpotNotFoundException e){
            System.out.println("Space not available for requested vehicle type");
        }

        Exit exit= new Exit(1);
        if(ticket2!=null){
            BigDecimal bigDecimal = exit.calculateCostAndUnPark(ticket2, PayStrategy.HOUR_BASED);
            System.out.println("Parking cost:"+bigDecimal);
        }

        parkingSpotManager.printAvailableParkingSpots();
    }

    private static void addParkingSpots(ParkingSpotManager parkingSpotManager) {
        parkingSpotManager.setFloorsParkingSpot(List.of(
                new ParkingSpot(1, 0, VehicleType.FOUR_WHEELER),
                new ParkingSpot(2, 0, VehicleType.FOUR_WHEELER),
                new ParkingSpot(3, 0, VehicleType.FOUR_WHEELER),
                new ParkingSpot(4, 0, VehicleType.TWO_WHEELER),
                new ParkingSpot(5, 0, VehicleType.TWO_WHEELER),
                new ParkingSpot(6, 0, VehicleType.THREE_WHEELER),
                new ParkingSpot(1, 1, VehicleType.FOUR_WHEELER),
                new ParkingSpot(2, 1, VehicleType.FOUR_WHEELER),
                new ParkingSpot(3, 1, VehicleType.TWO_WHEELER),
                new ParkingSpot(4, 1, VehicleType.TWO_WHEELER),
                new ParkingSpot(5, 1, VehicleType.THREE_WHEELER)));
    }
}