package lk.ijse.gdse.paymentservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.paymentservice.dto.PaymentDTO;
import lk.ijse.gdse.paymentservice.dto.TicketDTO;
import lk.ijse.gdse.paymentservice.entity.Payment;
import lk.ijse.gdse.paymentservice.entity.Ticket;
import lk.ijse.gdse.paymentservice.repository.PaymentRepo;
import lk.ijse.gdse.paymentservice.service.PaymentService;
import lk.ijse.gdse.paymentservice.service.exception.DuplicateRecordException;
import lk.ijse.gdse.paymentservice.service.exception.NotFoundException;
import lk.ijse.gdse.paymentservice.service.exception.ServiceUnavailableException;
import lk.ijse.gdse.paymentservice.service.impl.util.TicketStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ModelMapper mapper;
    @Override
    public PaymentDTO placePayment(PaymentDTO paymentDTO) {
        if (paymentRepo.existsById(paymentDTO.getPaymentId())){
            throw new DuplicateRecordException("Payment ID is already exists!");
        }

        Payment payment = mapper.map(paymentDTO, Payment.class);
        TicketDTO ticketDTO;

        try {
            ticketDTO = restTemplate.getForObject("http://TICKET-SERVICE/api/v1/ticket/searchByTicketId?ticketId=" + paymentDTO.getTicket(), TicketDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("Ticket not found: " + paymentDTO.getTicket());
        }

        if (ticketDTO != null) {
            ticketDTO.setStatus(TicketStatus.PAID);
            updateTicketStatus(ticketDTO);
        }

        payment.setTicket(mapper.map(ticketDTO, Ticket.class));
        return mapper.map(paymentRepo.save(payment), PaymentDTO.class);
    }

    @Override
    public boolean updateTicketStatus(TicketDTO ticketDTO){
        try {
            TicketDTO newDTO = restTemplate.postForObject("http://TICKET-SERVICE/api/v1/ticket/updateTicket", ticketDTO, TicketDTO.class);
            return newDTO != null;
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("Ticket not found " + ticketDTO.getTicketId());
        }  catch (RestClientException e) {
            // Handle more generic Rest client exceptions
            throw new ServiceUnavailableException("Error communicating with Ticket service "+ e);
        }
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepo.findAll().stream().map(payment -> mapper.map(payment,PaymentDTO.class)).toList();
    }
}
