package com.zenden.task_management_system.Classes.DTO;

import lombok.Data;

@Data
public class ReadTicketDTO {
    private long id;
    private ReadEventDTO event;
    private ParticipantDTO participant;
}
