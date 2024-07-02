package lk.ijse.microservice.userservice.service;

import lk.ijse.microservice.userservice.dto.UserDTO;
import lk.ijse.microservice.userservice.entity.User;

import java.util.List;

public interface UserService {
    void save(UserDTO userDTO);

    UserDTO findById(String id);

    List<UserDTO> findAll();

    void delete(String id);

    void update(UserDTO userDTO);

    boolean existsById(String id);
}
