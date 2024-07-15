package lk.ijse.gdse.vehicleservice.repository;

import lk.ijse.gdse.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<Vehicle,String> {
    Vehicle findByVehicleId(String vehicleId);
}
