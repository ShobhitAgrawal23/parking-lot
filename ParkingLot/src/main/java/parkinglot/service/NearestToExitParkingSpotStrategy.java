package parkinglot.service;

import parkinglot.model.VehicleType;
import parkinglot.service.spot.ParkingSpot;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NearestToExitParkingSpotStrategy implements ParkingSpotStrategy, Comparator<ParkingSpot> {

    @Override
    public ParkingSpot findParkingSpot(List<ParkingSpot> parkingSpots, VehicleType vehicleType) {
        parkingSpots.sort(this);
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getVehicleType().equals(vehicleType) && parkingSpot.isEmpty()) {
                return parkingSpot;
            }
        }
        return null;
    }

    @Override
    public int compare(ParkingSpot o1, ParkingSpot o2) {
        return o2.getId()-o1.getId();
    }
}
