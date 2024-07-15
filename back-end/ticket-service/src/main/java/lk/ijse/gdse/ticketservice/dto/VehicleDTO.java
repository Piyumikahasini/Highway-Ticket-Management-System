package lk.ijse.gdse.ticketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    private String vehicleId;
    private String userName;
    private String type;
    private String brand;
    private String engineCapacity;
}
