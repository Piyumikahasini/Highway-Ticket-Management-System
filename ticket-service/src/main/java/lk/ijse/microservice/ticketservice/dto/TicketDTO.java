package lk.ijse.microservice.ticketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private String id;
    private String vehicleId;
    private String userId;
    private String status;
    private LocalDateTime issueDate;
}
