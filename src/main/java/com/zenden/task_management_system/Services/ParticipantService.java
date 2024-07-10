package com.zenden.task_management_system.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zenden.task_management_system.Classes.Participant;
import com.zenden.task_management_system.Classes.DTO.ParticipantDTO;
import com.zenden.task_management_system.Mapper.Mapper;
import com.zenden.task_management_system.Repositories.ParticipantRepository;

@Service
public class ParticipantService {
    
    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private Mapper mapper;

    public ParticipantDTO getParticipantById(long id) {
        Participant participant = participantRepository.findById(id).get();
        return mapper.map(participant);
    }

    public Page<ParticipantDTO> getAllParticipants(int page, int size, String sortBy) {
        return participantRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy))).map(mapper::map);
    }
    public ParticipantDTO createParticipant(ParticipantDTO participantDTO) {
        Participant participant = mapper.map(participantDTO);
        participantRepository.save(participant);
        return mapper.map(participant);
    }

    public ParticipantDTO updateParticipant(long id, ParticipantDTO participantDTO) {
        Optional<Participant> participant = participantRepository.findById(id);
        if (participant.isPresent()) {
            participant.get().setName(participantDTO.getName());
            participant.get().setEmail(participantDTO.getEmail());
            return mapper.map(participantRepository.save(participant.get()));
        } else {
            throw new RuntimeException("Participant not found");
        }
    }

    public void deleteParticipant(long id) {
        participantRepository.deleteById(id);
    }


}
