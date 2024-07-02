package lk.ijse.microservice.vehicleservice.repository;


import lk.ijse.microservice.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    boolean existsByUserId(String userId);

}
