package com.example.supportticket.service;

import com.example.supportticket.dto.TicketDtos.*;
import com.example.supportticket.exception.InvalidTransitionException;
import com.example.supportticket.exception.NotFoundException;
import com.example.supportticket.model.*;
import com.example.supportticket.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository tickets; private final CommentRepository comments;
    public TicketService(TicketRepository tickets, CommentRepository comments){this.tickets=tickets;this.comments=comments;}
    @Transactional public TicketResponse create(CreateTicketRequest r){ return response(tickets.save(new Ticket(r.title(),r.description(),r.priority(),r.assignee()))); }
    @Transactional(readOnly=true) public List<TicketResponse> list(String q, TicketStatus status){
        List<Ticket> result;
        if(q != null && !q.isBlank() && status != null) result=tickets.findByStatusAndTitleContainingIgnoreCaseOrStatusAndDescriptionContainingIgnoreCaseOrderByUpdatedAtDesc(status,q,status,q);
        else if(q != null && !q.isBlank()) result=tickets.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByUpdatedAtDesc(q,q);
        else if(status != null) result=tickets.findByStatusOrderByUpdatedAtDesc(status);
        else result=tickets.findAllByOrderByUpdatedAtDesc();
        return result.stream().map(this::response).toList();
    }
    @Transactional(readOnly=true) public TicketResponse get(Long id){return response(find(id));}
    @Transactional public TicketResponse update(Long id, UpdateTicketRequest r){var t=find(id);t.updateFields(r.title(),r.description(),r.priority(),r.assignee());return response(t);}
    @Transactional public TicketResponse transition(Long id, TicketStatus target){
        var t=find(id); try{t.transitionTo(target);}catch(IllegalStateException e){throw new InvalidTransitionException(e.getMessage());} return response(t);
    }
    @Transactional public CommentResponse addComment(Long id, AddCommentRequest r){var c=comments.save(new Comment(find(id),r.author(),r.body()));return new CommentResponse(c.getId(),c.getAuthor(),c.getBody(),c.getCreatedAt());}
    private Ticket find(Long id){return tickets.findById(id).orElseThrow(()->new NotFoundException("Ticket %d not found".formatted(id)));}
    private TicketResponse response(Ticket t){var cs=comments.findByTicketIdOrderByCreatedAtAsc(t.getId()).stream().map(c->new CommentResponse(c.getId(),c.getAuthor(),c.getBody(),c.getCreatedAt())).toList();return new TicketResponse(t.getId(),t.getTitle(),t.getDescription(),t.getPriority(),t.getAssignee(),t.getStatus(),t.getCreatedAt(),t.getUpdatedAt(),cs);}
}
