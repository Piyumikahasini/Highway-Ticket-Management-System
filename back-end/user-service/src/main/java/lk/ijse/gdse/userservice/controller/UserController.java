package lk.ijse.gdse.userservice.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse.userservice.dto.UserDTO;
import lk.ijse.gdse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public UserDTO saveUser(@Valid @RequestBody UserDTO userDTO) {
        System.out.println("UserDTO = "+userDTO);
        return userService.saveUser(userDTO);
    }

    @PatchMapping("/update")
    public UserDTO updateUser(@Valid @RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/delete")
    public boolean deleteUser(@RequestParam("userName") String userName){
        return userService.deleteUser(userName);
    }

    @GetMapping("/getAllUsers")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/searchByUserName")
    public UserDTO searchUserByUserName(@RequestParam("userName") String userName){
        return userService.searchUserByUserName(userName);
    }
}
