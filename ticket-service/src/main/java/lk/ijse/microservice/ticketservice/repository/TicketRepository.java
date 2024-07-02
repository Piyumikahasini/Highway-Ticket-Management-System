package lk.ijse.microservice.ticketservice.repository;

import lk.ijse.microservice.ticketservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}
