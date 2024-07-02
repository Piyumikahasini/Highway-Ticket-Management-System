package lk.ijse.microservice.ticketservice.service;

import lk.ijse.microservice.ticketservice.dto.TicketDTO;
import lk.ijse.microservice.ticketservice.entity.Ticket;
import lk.ijse.microservice.ticketservice.repository.TicketRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    TicketRepository ticketRepository;
    RestTemplate restTemplate;
    ModelMapper modelMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, RestTemplate restTemplate, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        return modelMapper.map(ticketRepository.findAll(), new TypeToken<List<TicketDTO>>() {}.getType());
    }

    @Override
    public TicketDTO getTicketById(String id) {
        return this.modelMapper.map(ticketRepository.findById(id).get(), TicketDTO.class);
    }

    @Override
    public void saveTicket(TicketDTO ticket) {
        if (!ticketRepository.existsById(ticket.getId())) {
            Boolean isVehicleExist = restTemplate.getForObject("https://VEHICLE-SERVICE/api/v1/vehicles/checkVehicle/" + ticket.getVehicleId(), Boolean.class);
            Boolean isUserExist = restTemplate.getForObject("https://VEHICLE-SERVICE/api/v1/vehicles/checkUser/" + ticket.getUserId(), Boolean.class);

            if (Boolean.TRUE.equals(isVehicleExist) && Boolean.TRUE.equals(isUserExist)) {
                ticket.setIssueDate(LocalDateTime.now());
                ticket.setStatus("Not Paid");
                ticketRepository.save(modelMapper.map(ticket, Ticket.class));
            }else {
                throw new RuntimeException("Invalid ticket");
            }
        }else {
            throw new RuntimeException("Ticket already exists");
        }
    }

    @Override
    public void updateTicket(TicketDTO ticket) {
        if (ticketRepository.existsById(ticket.getId())) {
            Boolean isVehicleExist = restTemplate.getForObject("https://VEHICLE-SERVICE/api/v1/vehicles/checkVehicle/" + ticket.getVehicleId(), Boolean.class);
            Boolean isUserExist = restTemplate.getForObject("https://VEHICLE-SERVICE/api/v1/vehicles/checkUser/" + ticket.getUserId(), Boolean.class);

            if (Boolean.TRUE.equals(isVehicleExist) && Boolean.TRUE.equals(isUserExist)) {
                ticketRepository.save(modelMapper.map(ticket, Ticket.class));
            }else {
                throw new RuntimeException("Invalid ticket");
            }
        }else {
            throw new RuntimeException("Ticket does not exist");
        }
    }

    @Override
    public void deleteTicket(String id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
        }else {
            throw new RuntimeException("Ticket does not exist");
        }
    }

    @Override
    public boolean existsTicket(String id) {
        return ticketRepository.existsById(id);
    }

    @Override
    public void updateTicketStatus(String id) {
        Ticket ticket = ticketRepository.findById(id).get();
        ticket.setStatus("Paid");
        ticketRepository.save(ticket);
    }
}
