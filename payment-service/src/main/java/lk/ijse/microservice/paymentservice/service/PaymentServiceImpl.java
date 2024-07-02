package lk.ijse.microservice.paymentservice.service;

import lk.ijse.microservice.paymentservice.dto.PaymentDTO;
import lk.ijse.microservice.paymentservice.entity.Payment;
import lk.ijse.microservice.paymentservice.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    PaymentRepository paymentRepository;
    ModelMapper modelMapper;
    RestTemplate restTemplate;

    public PaymentServiceImpl(PaymentRepository paymentRepository, ModelMapper modelMapper, RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public void save(PaymentDTO paymentDTO) {
        if (!paymentRepository.existsById(paymentDTO.getId())) {
            Boolean isTicketExist = restTemplate.getForObject("http://localhost:8080/api/v1/tickets/checkTicket/" + paymentDTO.getTicketId(), Boolean.class);
            if (Boolean.TRUE.equals(isTicketExist)) {
                System.out.println("KKKKK");
                restTemplate.getForObject("http://localhost:8080/api/v1/tickets/updateStatus/" + paymentDTO.getTicketId(), String.class);
                paymentDTO.setPaymentDate(LocalDateTime.now());
                paymentRepository.save(modelMapper.map(paymentDTO, Payment.class));
            }else {
                throw new RuntimeException("Invalid ticket");
            }
        } else {
            throw new RuntimeException("Payment already exists");
        }
    }

    @Override
    public PaymentDTO getPaymentById(String id) {
        if (paymentRepository.existsById(id)) {
            return modelMapper.map(paymentRepository.getById(id), PaymentDTO.class);
        }else {
            throw new RuntimeException("payment not found");
        }

    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return modelMapper.map(paymentRepository.findAll(), new TypeToken<List<PaymentDTO>>() {}.getType());
    }

    @Override
    public void deletePaymentById(String id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
        }
    }

    @Override
    public void updatePayment(PaymentDTO paymentDTO) {
        if (paymentRepository.existsById(paymentDTO.getId())) {
            paymentRepository.save(modelMapper.map(paymentDTO, Payment.class));
        }else {
            throw new RuntimeException("payment not found");
        }
    }
}
