package com.example.supportticket.repository;

import com.example.supportticket.model.Ticket;
import com.example.supportticket.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByOrderByUpdatedAtDesc();
    List<Ticket> findByStatusOrderByUpdatedAtDesc(TicketStatus status);
    List<Ticket> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByUpdatedAtDesc(String title, String description);
    List<Ticket> findByStatusAndTitleContainingIgnoreCaseOrStatusAndDescriptionContainingIgnoreCaseOrderByUpdatedAtDesc(
        TicketStatus status1, String title, TicketStatus status2, String description);
}
