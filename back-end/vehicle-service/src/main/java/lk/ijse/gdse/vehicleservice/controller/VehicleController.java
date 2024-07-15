package lk.ijse.gdse.vehicleservice.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse.vehicleservice.dto.VehicleDTO;
import lk.ijse.gdse.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/getAllVehicles")
    public List<VehicleDTO> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @PostMapping("/saveVehicle")
    public VehicleDTO saveVehicle(@Valid @RequestBody VehicleDTO vehicleDTO){
        System.out.println("VehicleDTO = "+vehicleDTO);
        return vehicleService.saveVehicle(vehicleDTO);
    }

    @PatchMapping("/updateVehicle")
    public VehicleDTO updateVehicle(@Valid @RequestBody VehicleDTO vehicleDTO){
        return vehicleService.updateVehicle(vehicleDTO);
    }

    @DeleteMapping("/deleteVehicle")
    public boolean deleteVehicle(@RequestParam("vehicleId") String vehicleId){
        return vehicleService.deleteVehicle(vehicleId);
    }

    @GetMapping("/searchByVehicleId")
    public VehicleDTO searchByVehicleId(@RequestParam("vehicleId") String vehicleId){
        return vehicleService.searchVehicleById(vehicleId);
    }
}
