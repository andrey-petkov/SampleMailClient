package org.example.service.impl;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Email;
import org.example.service.CryptoServiceProvider;
import org.example.service.EmailService;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class OutsideMailClient implements EmailService {
    private static final String disclaimer = "Confidential";
    private static final Integer numberOfRetries = 3;

    private BlockingQueue<Email> retryQueue;

    private boolean encryptWithAES = false;

    private boolean encryptWithDES = false;
    @Override
    public void send(Email email) {
        Random randomNumber = new Random();

        retryQueue = new ArrayBlockingQueue<>(3);

        log.info(String.format("Send to external resource: Email of type %s was send to %s", email.getEmailType(), email.getTo()));
        email.setBody(email.getBody() + disclaimer);

        if (encryptWithAES){
            CryptoServiceProvider.encryptAES(email);
        }
        if(encryptWithDES){
            CryptoServiceProvider.encryptDES(email);
        }

        try {
            retryQueue.put(email);
            int attempts = 0;
            while (!retryQueue.isEmpty()){
                attempts++;
                if ( randomNumber.nextInt(100) < 50){
                    log.info("Failed to send e-mail");
                    if(attempts <= numberOfRetries) {
                        log.info(String.format("Attempt %d", attempts));
                    }
                    else {
                        log.info("Maximum number of retries reached");
                        break;
                    }
                }
                else{
                    log.info("Send e-mail was successful");
                    retryQueue.remove(email);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEncryptWithAES(boolean encryptWithAES) {
        this.encryptWithAES = encryptWithAES;
    }

    public void setEncryptWithDES(boolean encryptWithDES) {
        this.encryptWithDES = encryptWithDES;
    }
}
