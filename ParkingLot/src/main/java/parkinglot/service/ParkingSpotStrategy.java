package parkinglot.service;

import parkinglot.model.VehicleType;
import parkinglot.service.spot.ParkingSpot;

import java.util.List;

public interface ParkingSpotStrategy {
    ParkingSpot findParkingSpot(List<ParkingSpot> parkingSpot, VehicleType vehicleType);
}
