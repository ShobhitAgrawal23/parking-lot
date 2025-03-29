package parkinglot.service;

import parkinglot.model.ParkStrategy;
import parkinglot.model.Vehicle;
import parkinglot.model.VehicleType;
import parkinglot.service.spot.ParkingSpot;

import java.util.List;

public class ParkingSpotManager {
    List<ParkingSpot> parkingSpots;
    private static ParkingSpotManager parkingSpotManager;
    private ParkingSpotManager() {
    }
    public static ParkingSpotManager getInstance() {
        if (parkingSpotManager == null) {
            parkingSpotManager = new ParkingSpotManager();
        }
        return parkingSpotManager;
    }
    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }
    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }
    public void add(ParkingSpot parkingSpot) {
        parkingSpots.add(parkingSpot);
    }
    public void remove(ParkingSpot parkingSpot) {
        parkingSpots.remove(parkingSpot);
    }
    public ParkingSpot findParkingSpot(ParkStrategy parkStrategy, VehicleType vehicleType) {
        ParkingSpotStrategy parkingSpotStrategy=new DefaultParkingSpotStrategy();
        if(parkStrategy.equals(ParkStrategy.DEFAULT)){
            parkingSpotStrategy = new DefaultParkingSpotStrategy();
        } else if (parkStrategy.equals(ParkStrategy.NEAREST_TO_EXIT)) {
            parkingSpotStrategy = new NearestToExitParkingSpotStrategy();
        }
        return parkingSpotStrategy.findParkingSpot(parkingSpots, vehicleType);
    }
    public ParkingSpot park(Vehicle vehicle, ParkStrategy parkStrategy) {
        ParkingSpot parkingSpot=findParkingSpot(parkStrategy, vehicle.getType());
        parkingSpot.park(vehicle);
        return parkingSpot;
    }
    public void unPark(ParkingSpot parkingSpot) {
        parkingSpot.unPark();
    }
    public boolean parkingSpotAvailable(VehicleType vehicleType) {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if(parkingSpot.getVehicleType().equals(vehicleType) && parkingSpot.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void printAvailableParkingSpots() {
        int twoWheelerAvailableParkingSpots = 0;
        int threeWheelerAvailableParkingSpots = 0;
        int fourWheelerAvailableParkingSpots = 0;
        for (ParkingSpot parkingSpot : parkingSpots) {
            if(!parkingSpot.isEmpty()){
                continue;
            }
            if(parkingSpot.getVehicleType().equals(VehicleType.TWO_WHEELER)) {
                twoWheelerAvailableParkingSpots++;
            }else if(parkingSpot.getVehicleType().equals(VehicleType.THREE_WHEELER)) {
                threeWheelerAvailableParkingSpots++;
            }else if(parkingSpot.getVehicleType().equals(VehicleType.FOUR_WHEELER)) {
                fourWheelerAvailableParkingSpots++;
            }
        }
        System.out.println("-----------Parking Lot-----------");
        System.out.println("Two Wheeler Available ParkingSpots: " + twoWheelerAvailableParkingSpots);
        System.out.println("Three Wheeler Available ParkingSpots: " + threeWheelerAvailableParkingSpots);
        System.out.println("Four Wheeler Available ParkingSpots: " + fourWheelerAvailableParkingSpots);
        System.out.println("-----------Thank you-----------");
    }

}
