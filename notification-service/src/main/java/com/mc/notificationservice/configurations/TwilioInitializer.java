package com.mc.notificationservice.configurations;

import com.twilio.Twilio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TwilioInitializer {


    public TwilioInitializer(@Value("${twilio.account_sid}") String accountSid,
                             @Value("${twilio.auth_token}") String authToken){

        Twilio.init(accountSid, authToken);
        log.info("Twilio Initialized successfully... with accountSid {}",accountSid);
    }
}
