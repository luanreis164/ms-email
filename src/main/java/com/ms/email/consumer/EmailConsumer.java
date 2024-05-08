package com.ms.email.consumer;

import com.ms.email.dto.EmailRecordDto;
import com.ms.email.model.Email;
import com.ms.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }


    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto){
        var email = new Email();
        BeanUtils.copyProperties(emailRecordDto,email);
        emailService.sendEmail(email);
    }



}
