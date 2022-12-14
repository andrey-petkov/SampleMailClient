package org.example.domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.enumeration.EmailType;

@Data
@Builder
public class Email {
    private String from;
    private String to;
    private String subject;
    private String body;
    private EmailType emailType = EmailType.PLAIN_TEXT;
}
