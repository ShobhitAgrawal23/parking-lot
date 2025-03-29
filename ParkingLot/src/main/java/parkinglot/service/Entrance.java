package parkinglot.service;

import parkinglot.model.ParkStrategy;
import parkinglot.model.Ticket;
import parkinglot.model.Vehicle;
import parkinglot.model.VehicleType;
import parkinglot.service.spot.ParkingSpot;

import java.math.BigDecimal;

public class Entrance {
    private final ParkingSpotManager parkingSpotManager;

    public Entrance() {
        parkingSpotManager=ParkingSpotManager.getInstance();
    }

    public Ticket generateTicket(ParkStrategy strategy, VehicleType vehicleType, String vehicleNumber) {
        Vehicle vehicle = Vehicle.Builder.newInstance()
                .setNumber(vehicleNumber)
                .setType(vehicleType)
                .build();
        return Ticket.Builder.newInstance()
                .setVehicle(vehicle)
                .setParkingSpot(parkingSpotManager.park(vehicle, strategy))
                .setPrice(new BigDecimal(10))
                .build();
    }

    public boolean isParkingSpotAvailable(VehicleType vehicleType) {
        return parkingSpotManager.parkingSpotAvailable(vehicleType);
    }






}
