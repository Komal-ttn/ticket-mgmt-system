package com.example.supportticket.controller;

import com.example.supportticket.dto.TicketDtos.*;
import com.example.supportticket.model.TicketStatus;
import com.example.supportticket.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService service; public TicketController(TicketService service){this.service=service;}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public TicketResponse create(@Valid @RequestBody CreateTicketRequest r){return service.create(r);}
    @GetMapping public List<TicketResponse> list(@RequestParam(required=false) String q,@RequestParam(required=false) TicketStatus status){return service.list(q,status);}
    @GetMapping("/{id}") public TicketResponse get(@PathVariable Long id){return service.get(id);}
    @PatchMapping("/{id}") public TicketResponse update(@PathVariable Long id,@Valid @RequestBody UpdateTicketRequest r){return service.update(id,r);}
    @PostMapping("/{id}/comments") @ResponseStatus(HttpStatus.CREATED) public CommentResponse comment(@PathVariable Long id,@Valid @RequestBody AddCommentRequest r){return service.addComment(id,r);}
    @PostMapping("/{id}/transitions") public TicketResponse transition(@PathVariable Long id,@Valid @RequestBody TransitionRequest r){return service.transition(id,r.status());}
}
