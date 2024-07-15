package lk.ijse.gdse.ticketservice.dto;

import lk.ijse.gdse.ticketservice.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String paymentId;
    private String ticket;
    private String paymentMethod;
    private String paymentTime;
    private Double amount;
}
