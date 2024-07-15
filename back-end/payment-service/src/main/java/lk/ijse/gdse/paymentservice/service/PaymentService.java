package lk.ijse.gdse.paymentservice.service;

import lk.ijse.gdse.paymentservice.dto.PaymentDTO;
import lk.ijse.gdse.paymentservice.dto.TicketDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO placePayment(PaymentDTO paymentDTO);
    boolean updateTicketStatus(TicketDTO ticketDTO);
    List<PaymentDTO> getAllPayments();
}
