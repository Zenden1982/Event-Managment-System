package com.zenden.task_management_system.Classes.DTO;

import lombok.Value;

@Value
public class CreateEditUpdateTicketDTO {

    private Long id;
    private Long eventId;
    private Long participantId;
}
