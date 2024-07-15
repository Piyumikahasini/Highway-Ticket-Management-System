package lk.ijse.gdse.paymentservice.dto;

import lk.ijse.gdse.paymentservice.service.impl.util.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private String ticketId;
    private String user;
    private String vehicle;
    private String date;
    private String time;
    private Double amount;
    private String entryPoint;
    private String exitPoint;
    private TicketStatus status;
}
