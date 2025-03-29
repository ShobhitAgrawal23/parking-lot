package parkinglot.model;

import parkinglot.service.spot.ParkingSpot;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

public class Ticket {
    private UUID id;
    private BigDecimal price;
    private OffsetDateTime entryTime;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;

    public Ticket(Builder builder) {
        id = UUID.randomUUID();
        entryTime = OffsetDateTime.now();
        price = builder.price;
        vehicle = builder.vehicle;
        parkingSpot = builder.parkingSpot;
    }

    public static class Builder {
        private BigDecimal price;
        private Vehicle vehicle;
        private ParkingSpot parkingSpot;

        public static Builder newInstance()
        {
            return new Builder();
        }

        private Builder() {}

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

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
