package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.TicketRepository;

/**
 * REST controller class for handling drawing service status requests.
 */
@RestController
public class DrawingServiceController {
   @Autowired
   TicketRepository ticketRepo;
   /**
    * Handles GET requests to "/status" endpoint.
    *
    * @return A JSON string containing the current server status.
    */
   @RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
   public String status() {
      return "{\"status\":\"Greetings from Spring Boot. "
            + java.time.LocalDate.now() + ", "
            + java.time.LocalTime.now()
            + ". " + "The server is Runnig!\"}";
   }

   @GetMapping("/getticket")
   public String getTicket() {
      return "{\"ticket\":\"" + ticketRepo.getTicket() + "\"}";
   }
}
