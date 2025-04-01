package parkinglot.service;

import parkinglot.model.Ticket;
import parkinglot.model.Vehicle;

import java.math.BigDecimal;

public interface PaymentStrategy {
    BigDecimal calculatePayment(Ticket ticket);
}
