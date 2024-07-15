package lk.ijse.gdse.userservice.service;

import lk.ijse.gdse.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    boolean deleteUser(String userName);
    List<UserDTO> getAllUsers();
    UserDTO searchUserByUserName(String userName);
}
