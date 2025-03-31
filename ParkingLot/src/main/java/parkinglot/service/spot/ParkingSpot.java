package parkinglot.service.spot;

import parkinglot.model.Vehicle;
import parkinglot.model.VehicleType;

import java.util.concurrent.locks.ReentrantLock;

public class ParkingSpot {
    private int id;
    private int floorNumber;
    private VehicleType vehicleType;
    private boolean isEmpty;
    private Vehicle vehicle;
    private final ReentrantLock lock = new ReentrantLock();

    public ParkingSpot(int id, int floorNumber, VehicleType vehicleType ) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.vehicleType = vehicleType;
        this.isEmpty = true;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
    public Vehicle getVehicle() {
        return vehicle;
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
    public boolean park(Vehicle vehicle){
        lock.lock();
        try {
            if(isEmpty){
                this.vehicle = vehicle;
                isEmpty = false;
                return true;
            }
            return false;
        }finally {
            lock.unlock();
        }
    }
    public void unPark(){
        this.vehicle = null;
        isEmpty = true;
    };
}
