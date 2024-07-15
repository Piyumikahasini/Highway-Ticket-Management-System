package lk.ijse.gdse.paymentservice.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse.paymentservice.dto.PaymentDTO;
import lk.ijse.gdse.paymentservice.service.PaymentService;
import lk.ijse.gdse.paymentservice.service.exception.DuplicateRecordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/placePayment")
    public PaymentDTO placePayment(@Valid @RequestBody PaymentDTO paymentDTO){
        System.out.println("PaymentDTO = "+paymentDTO);
        return paymentService.placePayment(paymentDTO);
    }

    @GetMapping("/getAllPayments")
    public List<PaymentDTO> getAllPayments(){
        return paymentService.getAllPayments();
    }
}
