package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER= LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(final Mail mail){
        LOGGER.info("Starting email preparation");
        try {
            //SimpleMailMessage mailMessage= createMailMessage(mail);
            javaMailSender.send(createMimeMessage(mail));
            LOGGER.info("Email has been sent.");
        }catch(MailException e) {
            LOGGER.error("Failed to process email sending: ",e.getMessage(),e);
        }
    }
    private SimpleMailMessage createMailMessage(final Mail mail){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mail.getMailTo());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()));
        ofNullable(mail.getToCc()).ifPresent(simpleMailMessage::setCc);
       // Optional.of(mail)
        //  .map(Mail::getToCc)
         // .ifPresent(simpleMailMessage::setCC);
        return simpleMailMessage;
    }

  private MimeMessagePreparator createMimeMessage(final Mail mail){
      return mimeMessage -> {
          MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage);
          messageHelper.setTo(mail.getMailTo());
          messageHelper.setSubject(mail.getSubject());
          messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()),true);
      };
  }
}
