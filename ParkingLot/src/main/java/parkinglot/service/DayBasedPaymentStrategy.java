package parkinglot.service;

import parkinglot.model.Ticket;
import parkinglot.model.VehicleType;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;

public class DayBasedPaymentStrategy implements PaymentStrategy {


    @Override
    public BigDecimal calculatePayment(Ticket ticket) {
        Duration duration = Duration.between(ticket.getEntryTime(), OffsetDateTime.now());
        BigDecimal value= BigDecimal.valueOf(100.0);
        if(ticket.getVehicle().getType().equals(VehicleType.TWO_WHEELER)){
            value=value.multiply(BigDecimal.valueOf(2));
        }else if(ticket.getVehicle().getType().equals(VehicleType.THREE_WHEELER)){
            value=value.multiply(BigDecimal.valueOf(3));
        }else if(ticket.getVehicle().getType().equals(VehicleType.FOUR_WHEELER)){
            value=value.multiply(BigDecimal.valueOf(4));
        }
        return BigDecimal.valueOf(duration.toDays()+1).multiply(value);
    }
}
