package com.zenden.task_management_system.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zenden.task_management_system.Classes.Ticket;
import com.zenden.task_management_system.Classes.DTO.CreateEditUpdateTicketDTO;
import com.zenden.task_management_system.Classes.DTO.ReadTicketDTO;
import com.zenden.task_management_system.Mapper.Mapper;
import com.zenden.task_management_system.Repositories.EventRepository;
import com.zenden.task_management_system.Repositories.ParticipantRepository;
import com.zenden.task_management_system.Repositories.TicketRepository;

import jakarta.persistence.EntityManager;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    public ReadTicketDTO findById(long id) {
        return mapper.map(ticketRepository.findById(id).get());
    }
    
    public Page<ReadTicketDTO> getAllTickets(int page, int size, String sortBy) {
        return ticketRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy))).map(mapper::map);
    }

    public ReadTicketDTO createTicket(CreateEditUpdateTicketDTO readTicketDTO) {
        return mapper.map(ticketRepository.save(mapper.map(readTicketDTO)));
    }

    public ReadTicketDTO updateTicket(long id, CreateEditUpdateTicketDTO readTicketDTO) {
        Ticket ticket = ticketRepository.findById(id).get();
        ticket.setEvent(eventRepository.findById(readTicketDTO.getEventId()).get());
        ticket.setParticipant(participantRepository.findById(readTicketDTO.getParticipantId()).get());
        ticket = ticketRepository.save(ticket);
        entityManager.clear();  // Очистка контекста персистентности
        return mapper.map(ticketRepository.findById(ticket.getId()).get());
    }

    public void deleteTicket(long id) {
        ticketRepository.deleteById(id);
    }
}
