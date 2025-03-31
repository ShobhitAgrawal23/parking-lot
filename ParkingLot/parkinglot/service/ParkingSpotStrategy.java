package parkinglot.service;

import parkinglot.model.VehicleType;
import parkinglot.service.spot.ParkingSpot;

import java.util.List;
import java.util.Map;

public interface ParkingSpotStrategy {
    ParkingSpot findParkingSpot(Map<Integer, List<ParkingSpot>> floorWiseParkingSpots, VehicleType vehicleType);
}
