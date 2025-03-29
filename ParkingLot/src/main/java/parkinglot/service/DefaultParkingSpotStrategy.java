package parkinglot.service;

import parkinglot.model.VehicleType;
import parkinglot.service.spot.ParkingSpot;

import java.util.List;

public class DefaultParkingSpotStrategy implements ParkingSpotStrategy {

    @Override
    public ParkingSpot findParkingSpot(List<ParkingSpot> parkingSpots, VehicleType vehicleType) {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getVehicleType().equals(vehicleType) && parkingSpot.isEmpty()) {
                return parkingSpot;
            }
        }
        return null;
    }
}
