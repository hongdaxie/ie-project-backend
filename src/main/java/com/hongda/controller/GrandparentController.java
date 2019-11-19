package com.hongda.controller;

import com.hongda.dao.GrandparentDao;
import com.hongda.domain.Grandparent;
import com.hongda.service.MailService;
import com.hongda.service.SmsRequest;
import com.hongda.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class GrandparentController {

    @Autowired
    private GrandparentDao grandparentDao;
    private final TwilioService twilioService;

    public GrandparentController(TwilioService twilioService) {
        this.twilioService = twilioService;
    }

    @GetMapping("/grandparent/{id}")
    public Optional<Grandparent> getParentById(@PathVariable(value = "id") Long id){
        Optional<Grandparent> grandparent = grandparentDao.findById(id);
        grandparent.get().getUserid().setPassword("");
        return grandparent;
    }

    @GetMapping("/grandparent/findByUserid/{userid}")
    public List<Grandparent> getParentsByUserid(@PathVariable(value = "userid") Long userid){
        List<Grandparent> grandparents = grandparentDao.getAllByUserid(userid);
        for(Grandparent grandparent: grandparents){
            grandparent.getUserid().setPassword("");
        }
        return grandparents;
    }

    @PostMapping("/grandparent/addNewGrandparent")
    public Grandparent addNewGrandparent(@RequestBody Grandparent grandparent){
        return grandparentDao.save(grandparent);
    }

    @DeleteMapping("/grandparent/deleteGrandparentById/{id}")
    public Boolean deleteGrandparentById(@PathVariable(value = "id") Long id){
//        Optional<Grandparent> selectedGrandparent = getParentById(id);
//        if (selectedGrandparent.isPresent()){
//            grandparentDao.delete(selectedGrandparent.get());
//            return true;
//        }else {
//            return false;
//        }
        grandparentDao.deleteById(id);
        return true;
    }

    @PutMapping("/grandparent/updateGrandparent")
    public Grandparent updateGrandparent(@RequestBody Grandparent grandparent){
        return grandparentDao.save(grandparent);

    }

    @GetMapping("/grandparent/getAllEmailDistinct")
    public List<String> getAllEmailDistinct(){
        return grandparentDao.getAllDistinctEmail();
    }

    @GetMapping("/grandparent/sendNotification/{id}")
    public Map<String,String> sendNotificationById(@PathVariable(value = "id") Long id){
        Optional<Grandparent> optionalGrandparent = getParentById(id);
        Grandparent grandparent = optionalGrandparent.get();
        List<Grandparent> receiverList = new ArrayList<>();
        receiverList.add(grandparent);
        MailService mailService = new MailService();
        Boolean isEmailSent = mailService.sendEmailWithSendGrid(receiverList);
        Map<String,String> result = new HashMap<>();
        String phoneNumber = "+61" + grandparent.getPhoneNumber();
        String message = "Hi, " + grandparent.getName() + ". It`s " + grandparent.getUserid().getNickname() +  " here, I would like to remind you to take the vaccines for influenza as the flu season is about to start soon." ;
        SmsRequest smsRequest = new SmsRequest(phoneNumber, message);
        twilioService.sendSms(smsRequest);
        if (isEmailSent){
            result.put("success","You have sent notification to your" + grandparent.getRelationship() + "!");
        }else {
            result.put("error","Failed to send notification to your" + grandparent.getRelationship() + "!");
        }
        return result;
    }

}
