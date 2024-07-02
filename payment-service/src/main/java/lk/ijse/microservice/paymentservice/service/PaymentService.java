package lk.ijse.microservice.paymentservice.service;

import lk.ijse.microservice.paymentservice.dto.PaymentDTO;
import lk.ijse.microservice.paymentservice.entity.Payment;

import java.util.List;

public interface PaymentService {
    void save(PaymentDTO paymentDTO);

    PaymentDTO getPaymentById(String id);

    List<PaymentDTO> getAllPayments();

    void deletePaymentById(String id);

    void updatePayment(PaymentDTO paymentDTO);
}
