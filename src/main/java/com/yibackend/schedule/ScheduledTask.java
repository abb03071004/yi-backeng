package com.yibackend.schedule;


import com.yibackend.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Autowired
    private BookMapper bookMapper;


    @Scheduled(cron = "0 0 0 * * *")
    public void calculateScore(){
        BookMapper.calculateScore();
    }
}
