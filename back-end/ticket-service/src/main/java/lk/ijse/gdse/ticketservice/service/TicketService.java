package lk.ijse.gdse.ticketservice.service;

import lk.ijse.gdse.ticketservice.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    TicketDTO saveTicket(TicketDTO ticketDTO);
    TicketDTO updateTicket(TicketDTO ticketDTO);
    List<TicketDTO> getAllTickets();
    TicketDTO searchTicketByTicketId(String ticketId);

}
