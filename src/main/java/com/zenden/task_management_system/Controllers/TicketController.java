package com.zenden.task_management_system.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zenden.task_management_system.Classes.DTO.CreateEditUpdateTicketDTO;
import com.zenden.task_management_system.Classes.DTO.ReadTicketDTO;
import com.zenden.task_management_system.Services.TicketService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    
    @GetMapping
    @Transactional
    public ResponseEntity<Page<ReadTicketDTO>> findAll(@RequestParam(defaultValue = "0") int page, 
    @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue="id") String sortBy) {
        return ResponseEntity.ok(ticketService.getAllTickets(page, size, sortBy));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ReadTicketDTO> findById(@PathVariable long id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReadTicketDTO> create(@RequestBody CreateEditUpdateTicketDTO entity) {
        return ResponseEntity.ok(ticketService.createTicket(entity));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReadTicketDTO> update(@PathVariable long id, @RequestBody CreateEditUpdateTicketDTO entity) {
        return ResponseEntity.ok(ticketService.updateTicket(id, entity));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok("Deleted");
    }
}
