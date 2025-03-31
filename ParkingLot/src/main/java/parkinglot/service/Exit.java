package parkinglot.service;

import parkinglot.model.Ticket;

import java.math.BigDecimal;

public class Exit {
    int id;
    Ticket ticket;
    ParkingSpotManager parkingSpotManager;

    public Exit(int id) {
        this.id = id;
        parkingSpotManager=ParkingSpotManager.getInstance();
    }

    public BigDecimal calculateCostAndUnPark(Ticket ticket) {
        parkingSpotManager.unPark(ticket.getParkingSpot());
        return ticket.getPrice();
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }
}
