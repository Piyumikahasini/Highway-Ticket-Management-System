package lk.ijse.microservice.paymentservice.api;

import lk.ijse.microservice.paymentservice.dto.PaymentDTO;
import lk.ijse.microservice.paymentservice.entity.Payment;
import lk.ijse.microservice.paymentservice.service.PaymentService;
import lk.ijse.microservice.paymentservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseUtil savePayment(@RequestBody PaymentDTO payment) {
        paymentService.save(payment);
        return new ResponseUtil("200","Payment SuccessFully",null);
    }

    @GetMapping
    public ResponseUtil getAllPayments() {
        return new ResponseUtil("200","Fetch All Payments Successfully",paymentService.getAllPayments());
    }

    @GetMapping(params = "id",path = "/getPayment")
    public ResponseUtil getPaymentById(@RequestParam("id") String id) {
        return new ResponseUtil("200","Fetch Payment Successfully",paymentService.getPaymentById(id));
    }

    @PatchMapping
    public ResponseUtil updatePayment(@RequestBody PaymentDTO payment) {
        paymentService.updatePayment(payment);
        return new ResponseUtil("200","Payment Successfully Updated",null);
    }

    @DeleteMapping(params = "id")
    public ResponseUtil deletePaymentById(@RequestParam("id") String id) {
        paymentService.deletePaymentById(id);
        return new ResponseUtil("200","Payment Successfully Deleted",null);
    }
}
