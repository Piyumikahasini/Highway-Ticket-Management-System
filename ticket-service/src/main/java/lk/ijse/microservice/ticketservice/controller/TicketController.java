package lk.ijse.microservice.ticketservice.controller;

import lk.ijse.microservice.ticketservice.dto.TicketDTO;
import lk.ijse.microservice.ticketservice.service.TicketService;
import lk.ijse.microservice.ticketservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseUtil saveTicket(@RequestBody TicketDTO ticket) {
        ticketService.saveTicket(ticket);
        return new ResponseUtil("200", "Successfully Saved Ticket", null);
    }

    @GetMapping
    public ResponseUtil getAllTickets() {
        return new ResponseUtil("200", "Successfully Fetched All Tickets", ticketService.getAllTickets());
    }

    @GetMapping(params = "id", path = "/getTicket")
    public ResponseUtil getTicket(@RequestParam("id") String id) {
        return new ResponseUtil("200", "Successfully Fetched Ticket", ticketService.getTicketById(id));
    }

    @PatchMapping
    public ResponseUtil updateTicket(@RequestBody TicketDTO ticket) {
        ticketService.updateTicket(ticket);
        return new ResponseUtil("200", "Ticket Successfully Updated", null);
    }

    @DeleteMapping
    public ResponseUtil deleteTicket(@RequestParam("id") String id) {
        ticketService.deleteTicket(id);
        return new ResponseUtil("200", "Ticket Successfully Deleted", null);
    }

    @GetMapping("/updateStatus/{ticketId}")
    public ResponseUtil updateStatus(@PathVariable("ticketId") String ticketId) {
        ticketService.updateTicketStatus(ticketId);
        return new ResponseUtil("200", "Ticket Successfully Updated", null);
    }

    @GetMapping("/checkTicket/{checkTicketId}")
    public boolean checkTicketExistence(@PathVariable("checkTicketId") String checkTicketId) {
        return ticketService.existsTicket(checkTicketId);
    }
}
