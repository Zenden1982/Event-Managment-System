package com.zenden.task_management_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenden.task_management_system.Classes.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    

}
