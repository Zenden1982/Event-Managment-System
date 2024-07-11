package com.zenden.task_management_system.Classes.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReadTicketDTO {
    private long id;
    private ReadEventDTO event;
    private ParticipantDTO participant;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
