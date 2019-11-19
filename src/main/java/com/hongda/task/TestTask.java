package com.hongda.task;


import com.hongda.service.MailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {

    //https://www.freeformatter.com/cron-expression-generator-quartz.html
    @Scheduled(cron = "0 0 0 1 APR ?")
    public void sendAllEmail(){
//        System.out.println("now time :" + new Date());
        MailService mailService = new MailService();
//        mailService.sendEmailWithSendGrid();
    }

}
