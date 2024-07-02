package lk.ijse.microservice.vehicleservice.service;

import lk.ijse.microservice.vehicleservice.dto.VehicleDTO;
import lk.ijse.microservice.vehicleservice.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    public List<VehicleDTO> getAllVehicles();

    public VehicleDTO getVehicle(String vehicleId);

    public void saveVehicle(VehicleDTO vehicle);

    public void deleteVehicle(String vehicleId);

    void updateVehicle(VehicleDTO vehicle);

    boolean existVehicle(String vehicleId);

    boolean existUsers(String userId);
}
