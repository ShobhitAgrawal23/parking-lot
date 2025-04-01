package parkinglot.service;

import parkinglot.model.ParkStrategy;
import parkinglot.model.Ticket;
import parkinglot.model.Vehicle;
import parkinglot.model.VehicleType;

import java.math.BigDecimal;

public class Entrance {
    private int id;
    private final ParkingSpotManager parkingSpotManager;

    public Entrance(int id) {
        this.id=id;
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
                .build();
    }

    public boolean isParkingSpotAvailable(VehicleType vehicleType) {
        return parkingSpotManager.parkingSpotAvailable(vehicleType);
    }
    public int getId() {
        return id;
    }

}
