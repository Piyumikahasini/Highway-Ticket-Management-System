package lk.ijse.gdse.ticketservice.dto;

import jakarta.validation.constraints.*;
import lk.ijse.gdse.ticketservice.service.impl.util.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    @NotBlank(message = "Ticket ID cannot be blank")
    @Pattern(regexp = "^T-\\d{4}$", message = "Ticket ID must be in the format 'T-0001'")
    private String ticketId;
    @NotBlank(message = "UserId cannot be blank")
    private String user;

    @NotBlank(message = "VehicleId cannot be blank")
    private String vehicle;

    @NotBlank(message = "Date cannot be blank")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in the format 'YYYY-MM-DD'")
    private String date;

    @NotBlank(message = "Time cannot be blank")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Time must be in the format 'HH:MM'")
    private String time;

    @NotNull(message = "Amount cannot be null")
    @Pattern(regexp = "^[1-9]\\d*(\\.\\d{1,2})?$", message = "Amount must be a positive number like 2000.00")
    private String amount;

    @NotBlank(message = "Entry point cannot be blank")
    private String entryPoint;

    @NotBlank(message = "Exit point cannot be blank")
    private String exitPoint;

    @NotNull(message = "Status cannot be null")
    private TicketStatus status;
}
