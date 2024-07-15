package lk.ijse.gdse.vehicleservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    @NotBlank(message = "Vehicle ID cannot be blank")
    @Pattern(regexp = "^V-\\d{4}$", message = "Vehicle ID must be in the format 'V-0001'")
    private String vehicleId;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;

    @NotBlank(message = "Type cannot be blank")
    private String type;

    @NotBlank(message = "Brand cannot be blank")
    private String brand;

    @NotBlank(message = "Engine capacity cannot be blank")
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Engine capacity must be a number")
    private String engineCapacity;
}
