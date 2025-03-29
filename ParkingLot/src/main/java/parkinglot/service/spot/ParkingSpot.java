package parkinglot.service.spot;

import parkinglot.model.Vehicle;
import parkinglot.model.VehicleType;

public interface ParkingSpot {
    int getId();
    boolean isEmpty();
    VehicleType getVehicleType();
    void park(Vehicle vehicle);
    void unPark();
}
