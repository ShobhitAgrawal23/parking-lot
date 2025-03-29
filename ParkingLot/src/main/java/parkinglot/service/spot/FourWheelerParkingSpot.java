package parkinglot.service.spot;

import parkinglot.model.Vehicle;
import parkinglot.model.VehicleType;

public class FourWheelerParkingSpot implements ParkingSpot {

    private int id;
    private int floorNumber;
    private VehicleType vehicleType;
    private boolean isEmpty;
    private Vehicle vehicle;

    public FourWheelerParkingSpot(int id, int floorNumber) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.vehicleType = VehicleType.FOUR_WHEELER;
        this.isEmpty = true;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public void park(Vehicle vehicle) {
        this.vehicle = vehicle;
        isEmpty = false;
    }

    @Override
    public void unPark() {
        this.vehicle = null;
        isEmpty = true;
    }
}
