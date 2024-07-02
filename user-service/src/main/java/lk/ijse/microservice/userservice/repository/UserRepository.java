package lk.ijse.microservice.userservice.repository;

import lk.ijse.microservice.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
