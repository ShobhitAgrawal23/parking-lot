package parkinglot;

import parkinglot.model.ParkStrategy;
import parkinglot.model.Ticket;
import parkinglot.model.VehicleType;
import parkinglot.service.Entrance;
import parkinglot.service.ParkingSpotManager;
import parkinglot.service.spot.FourWheelerParkingSpot;
import parkinglot.service.spot.ThreeWheelerParkingSpot;
import parkinglot.service.spot.TwoWheelerParkingSpot;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ParkingSpotManager parkingSpotManager= ParkingSpotManager.getInstance();
        parkingSpotManager.setParkingSpots(List.of(
                new FourWheelerParkingSpot(1, 0),
                new FourWheelerParkingSpot(2, 0),
                new FourWheelerParkingSpot(3, 0),
                new TwoWheelerParkingSpot(4, 0),
                new TwoWheelerParkingSpot(5, 0),
                new ThreeWheelerParkingSpot(6, 0)));
        parkingSpotManager.printAvailableParkingSpots();
        Entrance entrance= new Entrance();
        try {
            Ticket ticket = entrance.generateTicket(ParkStrategy.DEFAULT, VehicleType.FOUR_WHEELER, "XYZ");
            System.out.println(ticket);
            parkingSpotManager.printAvailableParkingSpots();
            parkingSpotManager.unPark(ticket.getParkingSpot());
        }catch (Exception e){
            System.out.println("Space not available for requested vehicle type");
        }
        parkingSpotManager.printAvailableParkingSpots();
    }
}