package lk.ijse.microservice.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private String id;
    private String ticketId;
    private double amount;
    private LocalDateTime paymentDate;
}
