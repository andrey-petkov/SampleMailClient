package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.Email;

@Slf4j
public class CryptoServiceProvider {
    public static byte[] encryptAES(Email email){
        log.info("AES - encrypted mail!");
        return new byte[2];
    }

    public static byte[] encryptDES(Email email){
        log.info("DES - encrypted mail!");
        return new byte[2];
    }
}

