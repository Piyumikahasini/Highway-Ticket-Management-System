package lk.ijse.microservice.paymentservice.repository;

import lk.ijse.microservice.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
