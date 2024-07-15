package lk.ijse.gdse.paymentservice.entity;

import jakarta.persistence.*;
import lk.ijse.gdse.paymentservice.service.impl.util.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    private String ticketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userName", referencedColumnName = "userName")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicleId", referencedColumnName = "vehicleId")
    private Vehicle vehicle;

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    private String date;
    private String time;
    private Double amount;
    private String entryPoint;
    private String exitPoint;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}
