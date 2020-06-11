package com.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    @Scheduled(cron = "0 * * * * 1-7")
    public void time(){
        System.out.println("定时到了");
    }
}
