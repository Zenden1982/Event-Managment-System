package com.zenden.task_management_system.Classes.Sheluder;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    
    @Scheduled(fixedRate = 50000)
    public void reportCurrentTime() {
        System.out.println("The time is now " + LocalDateTime.now());
    }
}
