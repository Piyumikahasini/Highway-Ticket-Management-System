package lk.ijse.gdse.ticketservice.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse.ticketservice.dto.TicketDTO;
import lk.ijse.gdse.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/saveTicket")
    public TicketDTO saveTicket(@Valid @RequestBody TicketDTO ticketDTO){
        System.out.println("TicketDTO = "+ticketDTO);
        return ticketService.saveTicket(ticketDTO);
    }

    @PostMapping("/updateTicket")
    public TicketDTO updateTicket(@Valid @RequestBody TicketDTO ticketDTO){
        return ticketService.updateTicket(ticketDTO);
    }

    @GetMapping("/getAllTickets")
    public List<TicketDTO> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("/searchByTicketId")
    public TicketDTO searchByTicketId(@RequestParam("ticketId") String ticketId){
        return ticketService.searchTicketByTicketId(ticketId);
    }
}
