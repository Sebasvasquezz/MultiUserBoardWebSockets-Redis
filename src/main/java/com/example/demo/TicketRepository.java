package com.example.demo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class TicketRepository {
    @Autowired StringRedisTemplate template;


    // inject the template as ListOperations
    @Resource(name = "stringRedisTemplate") ListOperations<String, String> listTickets;
    int ticketnumber;

    public void setListTickets(ListOperations<String, String> listTickets) {
        this.listTickets = listTickets;
    }
    public TicketRepository() {
    }

    public synchronized Integer getTicket() {
        Integer a = ticketnumber++;
        listTickets.leftPush("ticketStore", a.toString());
        return a;
    }

    public boolean checkTicket(String ticket) {
        Long isValid = listTickets.getOperations().boundListOps("ticketStore").remove(0, ticket);
        return (isValid > 0l);
    }

}
