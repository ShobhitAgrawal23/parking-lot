package parkinglot.service.spot;

import parkinglot.model.Vehicle;
import parkinglot.model.VehicleType;

public class ParkingSpot {
    private int id;
    private int floorNumber;
    private VehicleType vehicleType;
    private boolean isEmpty;
    private Vehicle vehicle;

    public ParkingSpot(int id, int floorNumber, VehicleType vehicleType ) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.vehicleType = vehicleType;
        this.isEmpty = true;
    }

    public int getId(){
        return id;
    }
    public boolean isEmpty(){
        return isEmpty;
    }
    public VehicleType getVehicleType(){
        return vehicleType;
    }
    public void park(Vehicle vehicle){
        this.vehicle = vehicle;
        isEmpty = false;
    }
    public void unPark(){
        this.vehicle = null;
        isEmpty = true;
    };
}
