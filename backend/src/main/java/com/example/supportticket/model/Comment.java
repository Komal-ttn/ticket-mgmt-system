package com.example.supportticket.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "ticket_comments")
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "ticket_id", nullable = false) private Ticket ticket;
    @Column(nullable = false, length = 120) private String author;
    @Column(nullable = false, length = 2000) private String body;
    @Column(nullable = false, updatable = false) private Instant createdAt;
    protected Comment() {}
    public Comment(Ticket ticket, String author, String body) { this.ticket=ticket; this.author=author; this.body=body; }
    @PrePersist void onCreate(){createdAt=Instant.now();}
    public Long getId(){return id;} public String getAuthor(){return author;} public String getBody(){return body;} public Instant getCreatedAt(){return createdAt;}
}
