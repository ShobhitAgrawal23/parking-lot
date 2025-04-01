package parkinglot.model;

import parkinglot.service.spot.ParkingSpot;


import java.time.OffsetDateTime;
import java.util.UUID;

public class Ticket {
    private String id;
    private OffsetDateTime entryTime;
    private ParkingSpot parkingSpot;
    private Vehicle vehicle;

    public Ticket(Builder builder) {
        id = UUID.randomUUID().toString();
        entryTime = OffsetDateTime.now();
        vehicle = builder.vehicle;
        parkingSpot = builder.parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public OffsetDateTime getEntryTime() {
        return entryTime;
    }

    public static class Builder {
        private Vehicle vehicle;
        private ParkingSpot parkingSpot;

        public static Builder newInstance()
        {
            return new Builder();
        }

        private Builder() {}

        public Builder setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }
        public Builder setParkingSpot(ParkingSpot spot) {
            this.parkingSpot = spot;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }

    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
