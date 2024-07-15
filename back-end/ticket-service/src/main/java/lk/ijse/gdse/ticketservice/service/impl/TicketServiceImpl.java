package lk.ijse.gdse.ticketservice.service.impl;

import lk.ijse.gdse.ticketservice.dto.TicketDTO;
import lk.ijse.gdse.ticketservice.dto.UserDTO;
import lk.ijse.gdse.ticketservice.dto.VehicleDTO;
import lk.ijse.gdse.ticketservice.entity.Ticket;
import lk.ijse.gdse.ticketservice.entity.User;
import lk.ijse.gdse.ticketservice.entity.Vehicle;
import lk.ijse.gdse.ticketservice.repository.TicketRepo;
import lk.ijse.gdse.ticketservice.service.TicketService;
import lk.ijse.gdse.ticketservice.service.exception.DuplicateRecordException;
import lk.ijse.gdse.ticketservice.service.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ModelMapper mapper;
    @Override
    public TicketDTO saveTicket(TicketDTO ticketDTO) {
        if (ticketRepo.existsById(ticketDTO.getTicketId())){
            throw new DuplicateRecordException("Ticket ID is already exists!");
        }

        Ticket ticket = mapper.map(ticketDTO, Ticket.class);

        UserDTO userDTO;
        VehicleDTO vehicleDTO;

        try {
            userDTO = restTemplate.getForObject("http://USER-SERVICE/api/v1/user/searchByUserName?userName=" + ticketDTO.getUser(), UserDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("User not found: " + ticketDTO.getUser());
        }

        try {
            vehicleDTO = restTemplate.getForObject("http://VEHICLE-SERVICE/api/v1/vehicle/searchByVehicleId?vehicleId=" + ticketDTO.getVehicle(), VehicleDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("Vehicle not found: " + ticketDTO.getVehicle());
        }

        ticket.setUser(mapper.map(userDTO, User.class));
        ticket.setVehicle(mapper.map(vehicleDTO, Vehicle.class));

        return mapper.map(ticketRepo.save(ticket), TicketDTO.class);
    }

    @Override
    public TicketDTO updateTicket(TicketDTO ticketDTO) {
        if (!ticketRepo.existsById(ticketDTO.getTicketId())){
            throw new DuplicateRecordException("Ticket ID does not exists!");
        }

        Ticket ticket = mapper.map(ticketDTO, Ticket.class);

        UserDTO userDTO;
        VehicleDTO vehicleDTO;

        try {
            userDTO = restTemplate.getForObject("http://USER-SERVICE/api/v1/user/searchByUserName?userName=" + ticketDTO.getUser(), UserDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("User not found: " + ticketDTO.getUser());
        }

        try {
            vehicleDTO = restTemplate.getForObject("http://VEHICLE-SERVICE/api/v1/vehicle/searchByVehicleId?vehicleId=" + ticketDTO.getVehicle(), VehicleDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("Vehicle not found: " + ticketDTO.getVehicle());
        }

        ticket.setUser(mapper.map(userDTO, User.class));
        ticket.setVehicle(mapper.map(vehicleDTO, Vehicle.class));
        return mapper.map(ticketRepo.save(ticket), TicketDTO.class);
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        return ticketRepo.findAll().stream().map(ticket -> mapper.map(ticket, TicketDTO.class)).toList();
    }

    @Override
    public TicketDTO searchTicketByTicketId(String ticketId) {
        if (!ticketRepo.existsById(ticketId)){
            throw new NotFoundException("Ticket ID does not exists!");
        }
        return mapper.map(ticketRepo.findById(ticketId), TicketDTO.class);
    }
}
