package lk.ijse.gdse.vehicleservice.service.impl;

import lk.ijse.gdse.vehicleservice.dto.UserDTO;
import lk.ijse.gdse.vehicleservice.dto.VehicleDTO;
import lk.ijse.gdse.vehicleservice.entity.User;
import lk.ijse.gdse.vehicleservice.entity.Vehicle;
import lk.ijse.gdse.vehicleservice.repository.VehicleRepo;
import lk.ijse.gdse.vehicleservice.service.VehicleService;
import lk.ijse.gdse.vehicleservice.service.exception.DuplicateRecordException;
import lk.ijse.gdse.vehicleservice.service.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ModelMapper mapper;

    @Override
    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
        UserDTO userDTO;
        try {
            userDTO = restTemplate.getForObject("http://USER-SERVICE/api/v1/user/searchByUserName?userName=" + vehicleDTO.getUserName(), UserDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("User not found: " + vehicleDTO.getUserName());
        }

        if (vehicleRepo.existsById(vehicleDTO.getVehicleId())){
            throw new DuplicateRecordException("Vehicle ID is already exists!");
        }
        Vehicle vehicle = mapper.map(vehicleDTO, Vehicle.class);
        vehicle.setUserName(mapper.map(userDTO, User.class));
        return mapper.map(vehicleRepo.save(vehicle), VehicleDTO.class);
    }

    @Override
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        if (!vehicleRepo.existsById(vehicleDTO.getVehicleId())){
            throw new NotFoundException("Vehicle ID does not exists!");
        }
        return mapper.map(vehicleRepo.save(mapper.map(vehicleDTO, Vehicle.class)), VehicleDTO.class);
    }

    @Override
    public boolean deleteVehicle(String vehicleId) {
        if (!vehicleRepo.existsById(vehicleId)) {
            throw new NotFoundException("Vehicle ID does not exists!");
        }
        vehicleRepo.deleteById(vehicleId);
        return !vehicleRepo.existsById(vehicleId);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepo.findAll().stream().map(vehicle -> mapper.map(vehicle, VehicleDTO.class)).toList();
    }

    @Override
    public VehicleDTO searchVehicleById(String vehicleId) {
        if (!vehicleRepo.existsById(vehicleId)){
            throw new NotFoundException("Vehicle ID does not exists!");
        }
        return mapper.map(vehicleRepo.findById(vehicleId), VehicleDTO.class);
    }
}
