package lk.ijse.microservice.userservice.service;

import jakarta.transaction.Transactional;
import lk.ijse.microservice.userservice.dto.UserDTO;
import lk.ijse.microservice.userservice.entity.User;
import lk.ijse.microservice.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(UserDTO userDTO) {
        if (!userRepository.existsById(userDTO.getNic())) {
            userRepository.save(modelMapper.map(userDTO, User.class));
        } else {
            throw new RuntimeException("User already exists");
        }
    }

    @Override
    public UserDTO findById(String id) {
        if (userRepository.existsById(id)) {
            return modelMapper.map(userRepository.findById(id).get(), UserDTO.class);
        } else {
            throw new RuntimeException("User does not exist");
        }
    }

    @Override
    public List<UserDTO> findAll() {
        return modelMapper.map(userRepository.findAll(), new TypeToken<List<UserDTO>>() {
        }.getType());
    }

    @Override
    public void delete(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User does not exist");
        }
    }

    @Override
    public void update(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.getNic())) {
            userRepository.save(modelMapper.map(userDTO, User.class));
        }else {
            throw new RuntimeException("User does not exist");
        }
    }

    @Override
    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }
}
