package lk.ijse.gdse.vehicleservice.service;

import lk.ijse.gdse.vehicleservice.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    VehicleDTO saveVehicle(VehicleDTO vehicleDTO);
    VehicleDTO updateVehicle(VehicleDTO vehicleDTO);
    boolean deleteVehicle(String vehicleId);
    List<VehicleDTO> getAllVehicles();
    VehicleDTO searchVehicleById(String vehicleId);
}
