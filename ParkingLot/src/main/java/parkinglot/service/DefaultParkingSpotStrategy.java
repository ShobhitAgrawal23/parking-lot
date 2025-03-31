package parkinglot.service;

import parkinglot.model.VehicleType;
import parkinglot.service.spot.ParkingSpot;

import java.util.List;
import java.util.Map;

public class DefaultParkingSpotStrategy implements ParkingSpotStrategy {

    @Override
    public ParkingSpot findParkingSpot(Map<Integer, List<ParkingSpot>> floorWiseParkingSpots, VehicleType vehicleType) {
        for(Map.Entry<Integer, List<ParkingSpot>> parkingSpots: floorWiseParkingSpots.entrySet() ) {
            for (ParkingSpot parkingSpot : parkingSpots.getValue()) {
                if (parkingSpot.getVehicleType().equals(vehicleType) && parkingSpot.isEmpty()) {
                    return parkingSpot;
                }
            }
        }
        return null;
    }
}
