package com.example.supportticket.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "tickets")
public class Ticket {
    private static final Map<TicketStatus, Set<TicketStatus>> ALLOWED = Map.of(
        TicketStatus.OPEN, EnumSet.of(TicketStatus.IN_PROGRESS, TicketStatus.CANCELLED),
        TicketStatus.IN_PROGRESS, EnumSet.of(TicketStatus.RESOLVED, TicketStatus.CANCELLED),
        TicketStatus.RESOLVED, EnumSet.of(TicketStatus.CLOSED),
        TicketStatus.CLOSED, Set.of(), TicketStatus.CANCELLED, Set.of());

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200) private String title;
    @Column(nullable = false, length = 5000) private String description;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private Priority priority;
    @Column(length = 120) private String assignee;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private TicketStatus status = TicketStatus.OPEN;
    @Column(nullable = false, updatable = false) private Instant createdAt;
    @Column(nullable = false) private Instant updatedAt;

    protected Ticket() {}

    public Ticket(String title, String description, Priority priority, String assignee) {
        this.title = title; this.description = description; this.priority = priority; this.assignee = assignee;
        this.status = TicketStatus.OPEN;
    }

    @PrePersist void onCreate() { var now = Instant.now(); createdAt = now; updatedAt = now; }
    @PreUpdate void onUpdate() { updatedAt = Instant.now(); }

    public void updateFields(String title, String description, Priority priority, String assignee) {
        this.title = title; this.description = description; this.priority = priority; this.assignee = assignee;
    }
    public void transitionTo(TicketStatus target) {
        if (!ALLOWED.getOrDefault(status, Set.of()).contains(target))
            throw new IllegalStateException("Invalid transition from " + status + " to " + target);
        status = target;
    }
    public Long getId(){return id;} public String getTitle(){return title;} public String getDescription(){return description;}
    public Priority getPriority(){return priority;} public String getAssignee(){return assignee;} public TicketStatus getStatus(){return status;}
    public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
}
