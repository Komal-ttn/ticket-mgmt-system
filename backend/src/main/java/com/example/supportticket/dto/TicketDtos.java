package com.example.supportticket.dto;

import com.example.supportticket.model.Priority;
import com.example.supportticket.model.TicketStatus;
import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.List;

public final class TicketDtos {
    private TicketDtos() {}
    public record CreateTicketRequest(@NotBlank @Size(max=200) String title, @NotBlank @Size(max=5000) String description,
                                      @NotNull Priority priority, @Size(max=120) String assignee) {}
    public record UpdateTicketRequest(@NotBlank @Size(max=200) String title, @NotBlank @Size(max=5000) String description,
                                      @NotNull Priority priority, @Size(max=120) String assignee) {}
    public record TransitionRequest(@NotNull TicketStatus status) {}
    public record AddCommentRequest(@NotBlank @Size(max=120) String author, @NotBlank @Size(max=2000) String body) {}
    public record CommentResponse(Long id, String author, String body, Instant createdAt) {}
    public record TicketResponse(Long id, String title, String description, Priority priority, String assignee,
                                 TicketStatus status, Instant createdAt, Instant updatedAt, List<CommentResponse> comments) {}
}
