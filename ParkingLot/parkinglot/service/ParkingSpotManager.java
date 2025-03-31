package parkinglot.service;

import parkinglot.model.ParkStrategy;
import parkinglot.model.Ticket;
import parkinglot.model.Vehicle;
import parkinglot.model.VehicleType;
import parkinglot.service.spot.ParkingSpot;
import parkinglot.service.exception.SpotNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ParkingSpotManager {
    Map<Integer, List<ParkingSpot>> floorWiseParkingSpots;
    private static ParkingSpotManager parkingSpotManager;
    private ParkingSpotManager() {
        floorWiseParkingSpots = new TreeMap<>();
    }
    public static ParkingSpotManager getInstance() {
        if (parkingSpotManager == null) {
            parkingSpotManager = new ParkingSpotManager();
        }
        return parkingSpotManager;
    }
    public void setFloorsParkingSpot(List<ParkingSpot> parkingSpots) {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (floorWiseParkingSpots.containsKey(parkingSpot.getFloorNumber())) {
                floorWiseParkingSpots.get(parkingSpot.getFloorNumber()).add(parkingSpot);
            }else{
                List<ParkingSpot> floorWiseParkingSpotList = new ArrayList<>();
                floorWiseParkingSpotList.add(parkingSpot);
                floorWiseParkingSpots.put(parkingSpot.getFloorNumber(), floorWiseParkingSpotList);
            }
        }
    }
    public Map<Integer, List<ParkingSpot>> getFloorsParkingSpots() {
        return floorWiseParkingSpots;
    }
    public void add(ParkingSpot parkingSpot, Integer floorNumber) {
        if(floorWiseParkingSpots.containsKey(floorNumber)) {
            floorWiseParkingSpots.get(floorNumber).add(parkingSpot);
            floorWiseParkingSpots.put(floorNumber, floorWiseParkingSpots.get(floorNumber));
        } else {
            floorWiseParkingSpots.put(floorNumber, List.of(parkingSpot));
        }

    }
    public void remove(ParkingSpot parkingSpot) {
        if(floorWiseParkingSpots.containsKey(parkingSpot.getFloorNumber())) {
            floorWiseParkingSpots.get(parkingSpot.getFloorNumber()).remove(parkingSpot);
        }else{
            System.out.println("No such parking spot found, parkingSpotId: " + parkingSpot.getId());
        }

    }
    public ParkingSpot findParkingSpot(ParkStrategy parkStrategy, VehicleType vehicleType) {
        ParkingSpotStrategy parkingSpotStrategy=new DefaultParkingSpotStrategy();
        if(parkStrategy.equals(ParkStrategy.DEFAULT)){
            parkingSpotStrategy = new DefaultParkingSpotStrategy();
        } else if (parkStrategy.equals(ParkStrategy.NEAREST_TO_EXIT)) {
            parkingSpotStrategy = new NearestToExitParkingSpotStrategy();
        }
        return parkingSpotStrategy.findParkingSpot(floorWiseParkingSpots, vehicleType);
    }
    public ParkingSpot park(Vehicle vehicle, ParkStrategy parkStrategy) throws SpotNotFoundException {
        ParkingSpot parkingSpot=findParkingSpot(parkStrategy, vehicle.getType());
        if(parkingSpot==null || !parkingSpot.park(vehicle)){
            throw new SpotNotFoundException("Space not available for "+ vehicle.getType().toString());
        }

        return parkingSpot;
    }
    public void unPark(ParkingSpot parkingSpot) {
        parkingSpot.unPark();
    }
    public boolean parkingSpotAvailable(VehicleType vehicleType) {
        for(Map.Entry<Integer, List<ParkingSpot>> parkingSpots: floorWiseParkingSpots.entrySet() ) {
            for (ParkingSpot parkingSpot : parkingSpots.getValue()) {
                if (parkingSpot.getVehicleType().equals(vehicleType) && parkingSpot.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printAvailableParkingSpots() {
        int twoWheelerAvailableParkingSpots = 0;
        int threeWheelerAvailableParkingSpots = 0;
        int fourWheelerAvailableParkingSpots = 0;
        for(Map.Entry<Integer, List<ParkingSpot>> parkingSpots: floorWiseParkingSpots.entrySet() ) {
            for (ParkingSpot parkingSpot : parkingSpots.getValue()) {
                if (!parkingSpot.isEmpty()) {
                    continue;
                }
                if (parkingSpot.getVehicleType().equals(VehicleType.TWO_WHEELER)) {
                    twoWheelerAvailableParkingSpots++;
                } else if (parkingSpot.getVehicleType().equals(VehicleType.THREE_WHEELER)) {
                    threeWheelerAvailableParkingSpots++;
                } else if (parkingSpot.getVehicleType().equals(VehicleType.FOUR_WHEELER)) {
                    fourWheelerAvailableParkingSpots++;
                }
            }
        }
        System.out.println("-----------Parking Lot-----------");
        System.out.println("Two Wheeler Available ParkingSpots: " + twoWheelerAvailableParkingSpots);
        System.out.println("Three Wheeler Available ParkingSpots: " + threeWheelerAvailableParkingSpots);
        System.out.println("Four Wheeler Available ParkingSpots: " + fourWheelerAvailableParkingSpots);
        System.out.println("-----------Thank you-----------");
    }

}
