package lk.ijse.microservice.ticketservice.service;

import lk.ijse.microservice.ticketservice.dto.TicketDTO;
import lk.ijse.microservice.ticketservice.entity.Ticket;

import java.util.List;

public interface TicketService {
    List<TicketDTO> getAllTickets();
    TicketDTO getTicketById(String id);
    void saveTicket(TicketDTO ticket);
    void updateTicket(TicketDTO ticket);
    void deleteTicket(String id);
    boolean existsTicket(String id);
    void updateTicketStatus(String id);
}
