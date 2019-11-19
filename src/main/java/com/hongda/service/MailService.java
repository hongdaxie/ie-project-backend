package com.hongda.service;

import com.hongda.domain.Grandparent;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@RestController
public class MailService {

    public Boolean sendEmailWithSendGrid(List<Grandparent> receiverList) {
//        List<String> receiverList= grandparentDao.getAllDistinctEmail();
        Email from = new Email("info@age50Vaccine.ml");
        String subject = "Flu vaccine reminder!";
        int count = 0;
        for (Grandparent receiver: receiverList){
            Email to = new Email(receiver.getEmail());
            //todos
            // Hi, 'receiver'.
            // It's sender here, I would like to remind you to take the vaccines for influenza as the flu season is about to start soon.
            // For more information please visit this website
            String emailContent = "<html><head></head><body>Hi "+ receiver.getName() + ". <br><br> It`s " + receiver.getUserid().getNickname() + " here.<br><br> I would like to remind you to take the vaccines for influenza as the flu season is about to start soon.<br><br> For more information please visit <a href='http://age50vaccine.ml'>this website</a>.</body></html>";
            Content content = new Content("text/html", emailContent);
            Mail mail = new Mail(from, subject, to, content);
            mail.setReplyTo(new Email("1993xiehongda@gmail.com"));
            mail.personalization.get(0).addSubstitution("-username-", "Some blog user");
            //        mail.setTemplateId(EMAIL_TEMPLATE_ID);
            Request request = new Request();
            Response response = null;
//
            SendGrid sendGrid = new SendGrid("API_KEYS");
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                response = sendGrid.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
                count++;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }
        System.out.println(count);
//        return "email was successfully send";
        return true;
    }
}
