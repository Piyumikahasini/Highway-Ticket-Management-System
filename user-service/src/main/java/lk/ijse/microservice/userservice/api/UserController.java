package lk.ijse.microservice.userservice.api;

import lk.ijse.microservice.userservice.dto.UserDTO;
import lk.ijse.microservice.userservice.service.UserService;
import lk.ijse.microservice.userservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseUtil save(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return new ResponseUtil("200", "Successfully Saved User", null);
    }

    @GetMapping("/{userId}")
    public boolean checkUserExistence(@PathVariable("userId") String userId) {
        return userService.existsById(userId);
    }

    @GetMapping
    public ResponseUtil getAllUsers() {
        return new ResponseUtil("200", "Successfully Retrieved All Users", userService.findAll());
    }

    @GetMapping(params = "userId", path = "/getUser")
    public ResponseUtil getUserById(@RequestParam("userId") String userId) {
        return new ResponseUtil("200", "Successfully Retrieved User", userService.findById(userId));
    }

    @PatchMapping
    public ResponseUtil update(@RequestBody UserDTO userDTO) {
        userService.update(userDTO);
        return new ResponseUtil("200", "Successfully Updated User", null);
    }

    @DeleteMapping(params = "userId")
    public ResponseUtil delete(@RequestParam("userId") String userId) {
        userService.delete(userId);
        return new ResponseUtil("200", "Successfully Deleted User", null);
    }
}
