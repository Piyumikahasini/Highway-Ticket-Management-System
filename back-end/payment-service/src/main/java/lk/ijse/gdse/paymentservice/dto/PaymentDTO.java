package lk.ijse.gdse.paymentservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    @NotBlank(message = "Payment ID cannot be blank")
    @Pattern(regexp = "^P-\\d{4}$", message = "Payment ID must be in the format 'P-0001'")
    private String paymentId;

    @NotBlank(message = "Ticket cannot be blank")
    private String ticket;

    @NotBlank(message = "Payment method cannot be blank")
    private String paymentMethod;

    @NotBlank(message = "Time cannot be blank")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Time must be in the format 'HH:MM'")
    private String paymentTime;

    @NotNull(message = "Amount cannot be null")
    @Pattern(regexp = "^[1-9]\\d*(\\.\\d{1,2})?$", message = "Amount must be a positive number like 2000.00")
    private String amount;
}
