package lk.ijse.microservice.vehicleservice.service;

import jakarta.transaction.Transactional;
import lk.ijse.microservice.vehicleservice.dto.VehicleDTO;
import lk.ijse.microservice.vehicleservice.entity.Vehicle;
import lk.ijse.microservice.vehicleservice.repository.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;
    RestTemplate restTemplate;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper, RestTemplate restTemplate) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return modelMapper.map(vehicleRepository.findAll(),new TypeToken<List<VehicleDTO>>(){}.getType());
    }

    @Override
    public VehicleDTO getVehicle(String vehicleId) {
        if (vehicleRepository.existsById(vehicleId)) {
            return modelMapper.map(vehicleRepository.findById(vehicleId).get(),VehicleDTO.class);
        } else {
            throw new RuntimeException("Vehicle Not Found!");
        }
    }

    @Override
    public void saveVehicle(VehicleDTO vehicle) {
        if (!vehicleRepository.existsById(modelMapper.map(vehicle,Vehicle.class).getVehicleId())) {
            Boolean isOwnerValid = restTemplate.getForObject("http://USER-SERVICE/api/v1/users/"+vehicle.getUserId(), Boolean.class);
            System.out.println(isOwnerValid);
            if (Boolean.TRUE.equals(isOwnerValid)) {
                vehicleRepository.save(modelMapper.map(vehicle,Vehicle.class));
            }else {
                throw new RuntimeException("user Not Found!");
            }
        } else {
            throw new RuntimeException("Vehicle already exists!");
        }
    }

    @Override
    public void deleteVehicle(String vehicleId) {
        if (vehicleRepository.existsById(vehicleId)) {
            vehicleRepository.deleteById(vehicleId);
        } else {
            throw new RuntimeException("Vehicle Not Found!");
        }
    }

    @Override
    public void updateVehicle(VehicleDTO vehicle) {
        if (vehicleRepository.existsById(modelMapper.map(vehicle,Vehicle.class).getVehicleId())) {
            Vehicle vehicle1 = vehicleRepository.findById(vehicle.getVehicleId()).get();
            if (vehicle1.getUserId().equals(vehicle.getUserId())){
                vehicleRepository.save(modelMapper.map(vehicle,Vehicle.class));
            }else {
                throw new RuntimeException("Can't update the User Id!");
            }
        } else {
            throw new RuntimeException("Vehicle Not Found!");
        }
    }

    @Override
    public boolean existVehicle(String vehicleId) {
        return vehicleRepository.existsById(vehicleId);
    }

    @Override
    public boolean existUsers(String userId) {
        return vehicleRepository.existsByUserId(userId);
    }
}
