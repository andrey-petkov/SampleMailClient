package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.Email;
import org.example.service.CryptoServiceProvider;
import org.example.service.EmailService;
@Slf4j
public class InternalMailClient implements EmailService {

    @Override
    public void send(Email email) {
        log.info("Send email to internal resource");
        CryptoServiceProvider.encryptDES(email);
        log.info("Email send successfully");
    }
}
