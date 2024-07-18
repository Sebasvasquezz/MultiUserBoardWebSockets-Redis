package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
 
/**
 * Unit tests for {@link TicketRepository}.
 */
class TicketRepositoryTest {
 
    private TicketRepository ticketRepository;
 
    @Mock
    private StringRedisTemplate mockRedisTemplate;
 
    @Mock
    private ListOperations<String, String> mockListOperations;
 
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ticketRepository = new TicketRepository();
        ticketRepository.template = mockRedisTemplate;
        ticketRepository.listTickets = mockListOperations;
    }
 
    /**
     * Test case for getTicket method.
     */
    @Test
    void testGetTicket() {
        // Simulate leftPush operation
        when(mockListOperations.leftPush(anyString(), anyString())).thenReturn(1L);
 
        Integer ticketNumber = ticketRepository.getTicket();
 
        assertEquals(0, ticketNumber); // Assuming initial ticket number is 0
        verify(mockListOperations, times(1)).leftPush(eq("ticketStore"), eq("0"));
    }
}
 