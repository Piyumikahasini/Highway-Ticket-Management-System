package lk.ijse.microservice.vehicleservice.controller;

import lk.ijse.microservice.vehicleservice.dto.VehicleDTO;
import lk.ijse.microservice.vehicleservice.entity.Vehicle;
import lk.ijse.microservice.vehicleservice.service.VehicleService;
import lk.ijse.microservice.vehicleservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseUtil save(@RequestBody VehicleDTO vehicle) {
        vehicleService.saveVehicle(vehicle);
        return new ResponseUtil("200","SuccessFully saved vehicle",null);
    }

    @GetMapping
    public ResponseUtil getAll() {
        return new ResponseUtil("200","Successfully fetch all vehicles",vehicleService.getAllVehicles());
    }

    @GetMapping(params = "vehicleId", path = "/getVehicle")
    public ResponseUtil getVehicleById(@RequestParam("vehicleId") String vehicleId) {
        return new ResponseUtil("200","Successfully fetch vehicle",vehicleService.getVehicle(vehicleId));
    }

    @PatchMapping
    public ResponseUtil update(@RequestBody VehicleDTO vehicle) {
        vehicleService.updateVehicle(vehicle);
        return new ResponseUtil("200","Successfully updated vehicle",null);
    }

    @DeleteMapping
    public ResponseUtil delete(@RequestParam("vehicleId") String vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseUtil("200","Successfully deleted vehicle",null);
    }

    @GetMapping(path = "/checkVehicle/{vehicleId}")
    public boolean checkVehicleExistence(@PathVariable("vehicleId") String vehicleId) {
        return vehicleService.existVehicle(vehicleId);
    }

    @GetMapping(path = "/checkUser/{userId}")
    public boolean checkUserExistence(@PathVariable("userId") String userId) {
        return vehicleService.existUsers(userId);
    }
}
