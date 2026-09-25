package com.example.supportticket;

import com.example.supportticket.model.*;
import com.example.supportticket.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest @AutoConfigureMockMvc
class TicketApiIntegrationTest {
 @Autowired MockMvc mvc; @Autowired TicketRepository repo;
 @BeforeEach void clear(){repo.deleteAll();}
 @Test void createAndRejectInvalidTransition() throws Exception {
   mvc.perform(post("/api/tickets").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"Printer\",\"description\":\"Broken\",\"priority\":\"HIGH\"}"))
      .andExpect(status().isCreated()).andExpect(jsonPath("$.status",is("OPEN")));
   var id=repo.findAll().getFirst().getId();
   mvc.perform(post("/api/tickets/"+id+"/transitions").contentType(MediaType.APPLICATION_JSON).content("{\"status\":\"CLOSED\"}"))
      .andExpect(status().isConflict()).andExpect(jsonPath("$.title",is("Invalid state transition")));
 }
 @Test void validationFails() throws Exception {
   mvc.perform(post("/api/tickets").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"\",\"description\":\"\",\"priority\":\"HIGH\"}"))
      .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors.title").exists());
 }
 @Test void validTransitionPersists() throws Exception {
   mvc.perform(post("/api/tickets").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"T\",\"description\":\"D\",\"priority\":\"LOW\"}"));
   var id=repo.findAll().getFirst().getId();
   mvc.perform(post("/api/tickets/"+id+"/transitions").contentType(MediaType.APPLICATION_JSON).content("{\"status\":\"IN_PROGRESS\"}"))
      .andExpect(status().isOk()).andExpect(jsonPath("$.status",is("IN_PROGRESS")));
   assertEquals(TicketStatus.IN_PROGRESS,repo.findById(id).orElseThrow().getStatus());
 }
}
