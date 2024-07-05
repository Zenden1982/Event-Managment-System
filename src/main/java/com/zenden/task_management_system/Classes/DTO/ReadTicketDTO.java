package com.zenden.task_management_system.Classes.DTO;

import lombok.Value;

@Value
public class ReadTicketDTO {
    private long id;
    private ReadEventDTO event;
    private ParticipantDTO participant;
}
