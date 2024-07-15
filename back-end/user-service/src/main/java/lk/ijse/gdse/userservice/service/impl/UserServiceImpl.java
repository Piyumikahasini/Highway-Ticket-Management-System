package lk.ijse.gdse.userservice.service.impl;

import lk.ijse.gdse.userservice.dto.UserDTO;
import lk.ijse.gdse.userservice.entity.User;
import lk.ijse.gdse.userservice.repository.UserRepo;
import lk.ijse.gdse.userservice.service.UserService;
import lk.ijse.gdse.userservice.service.exception.DuplicateRecordException;
import lk.ijse.gdse.userservice.service.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if (userRepo.existsById(userDTO.getUserName())){
            throw new DuplicateRecordException("User Name is already exists!");
        }
        return mapper.map(userRepo.save(mapper.map(userDTO, User.class)), UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        if (!userRepo.existsById(userDTO.getUserName())){
            throw new NotFoundException("User Name does not exists!");
        }
        return mapper.map(userRepo.save(mapper.map(userDTO, User.class)), UserDTO.class);
    }

    @Override
    public boolean deleteUser(String userName) {
        if (!userRepo.existsById(userName)) {
            throw new NotFoundException("User Name does not exists!");
        }
        userRepo.deleteById(userName);
        return !userRepo.existsById(userName);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream().map(user -> mapper.map(user,UserDTO.class)).toList();
    }

    @Override
    public UserDTO searchUserByUserName(String userName) {
        if (!userRepo.existsById(userName)){
            throw new NotFoundException("User Name does not exists!");
        }
        return mapper.map(userRepo.findById(userName),UserDTO.class);
    }
}
