package com.example.supportticket;

import com.example.supportticket.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketStateMachineTest {
    private Ticket ticket(){return new Ticket("T","D",Priority.MEDIUM,"A");}
    @Test void allowedTransitions(){var t=ticket();t.transitionTo(TicketStatus.IN_PROGRESS);t.transitionTo(TicketStatus.RESOLVED);t.transitionTo(TicketStatus.CLOSED);assertEquals(TicketStatus.CLOSED,t.getStatus());}
    @Test void cancellationFromOpen(){var t=ticket();t.transitionTo(TicketStatus.CANCELLED);assertEquals(TicketStatus.CANCELLED,t.getStatus());}
    @Test void cancellationFromInProgress(){var t=ticket();t.transitionTo(TicketStatus.IN_PROGRESS);t.transitionTo(TicketStatus.CANCELLED);assertEquals(TicketStatus.CANCELLED,t.getStatus());}
    @Test void rejectsClosedToOpen(){var t=ticket();t.transitionTo(TicketStatus.IN_PROGRESS);t.transitionTo(TicketStatus.RESOLVED);t.transitionTo(TicketStatus.CLOSED);assertThrows(IllegalStateException.class,()->t.transitionTo(TicketStatus.OPEN));}
    @Test void rejectsResolvedToOpen(){var t=ticket();t.transitionTo(TicketStatus.IN_PROGRESS);t.transitionTo(TicketStatus.RESOLVED);assertThrows(IllegalStateException.class,()->t.transitionTo(TicketStatus.OPEN));}
    @Test void rejectsCancelledToOpen(){var t=ticket();t.transitionTo(TicketStatus.CANCELLED);assertThrows(IllegalStateException.class,()->t.transitionTo(TicketStatus.OPEN));}
    @Test void rejectsSkippingStates(){var t=ticket();assertThrows(IllegalStateException.class,()->t.transitionTo(TicketStatus.RESOLVED));assertThrows(IllegalStateException.class,()->t.transitionTo(TicketStatus.CLOSED));}
}
