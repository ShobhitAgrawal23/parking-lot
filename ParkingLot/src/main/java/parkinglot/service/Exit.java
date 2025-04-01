package parkinglot.service;

import parkinglot.model.ParkStrategy;
import parkinglot.model.PayStrategy;
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

    public BigDecimal calculateCostAndUnPark(Ticket ticket, PayStrategy payStrategy) {
        parkingSpotManager.unPark(ticket.getParkingSpot());
        PaymentStrategy paymentStrategy= getPaymentStrategy(payStrategy);
        return paymentStrategy.calculatePayment(ticket);
    }

    private PaymentStrategy getPaymentStrategy(PayStrategy parkStrategy) {
        switch (parkStrategy) {
            case DAY_BASED -> {
                return new DayBasedPaymentStrategy();
            }
            default -> {
                return new HourBasedPaymentStrategy();
            }
        }
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }
}
