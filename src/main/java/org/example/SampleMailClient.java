package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.Email;
import org.example.enumeration.EmailType;
import org.example.service.impl.InternalMailClient;
import org.example.service.impl.OutsideMailClient;

@Slf4j
public class SampleMailClient {
    public static void main(String[] args) {
        log.info("Sample mail client");
        Email plainTextEmail = Email.builder()
                .from("from@address.com")
                .to("to@address.com")
                .emailType(EmailType.PLAIN_TEXT)
                .subject("Plain text email")
                .body("Plain text email")
                .build();

        Email htmlEmail = Email.builder()
                .from("from@address.com")
                .to("to@address.com")
                .emailType(EmailType.HTML)
                .subject("Plain text email")
                .body("<p>HTML email</p>")
                .build();

        log.info("Send plaintext mail to internal resource");
        InternalMailClient companyMail = new InternalMailClient();
        companyMail.send(htmlEmail);

        OutsideMailClient worldMail = new OutsideMailClient();

        log.info("Send HTML mail to external resource");
        worldMail.setEncryptWithAES(false);
        worldMail.setEncryptWithDES(false);
        worldMail.send(plainTextEmail);

        log.info("Send HTML mail to external resource. AES encrypted");
        worldMail.setEncryptWithAES(true);
        worldMail.setEncryptWithDES(false);
        worldMail.send(htmlEmail);

        log.info("Send plaintext mail to external resource. AES and DES encrypted");
        worldMail.setEncryptWithAES(true);
        worldMail.setEncryptWithDES(true);
        worldMail.send(plainTextEmail);
    }
}
