package com.mc.notificationservice.notificationProvider.notificationProviderImpl;

import com.mc.notificationservice.model.Notification;
import com.mc.notificationservice.notificationProvider.NotificationInterface;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service("twilio")
@RequiredArgsConstructor
@Slf4j
public class TwilioService implements NotificationInterface {

    @Value("${twilio.trial_number}")
    private String FROM_NUMBER;

    @Override
    public void makeIVRCall(Notification notification) throws URISyntaxException {
        // demo purpose sending an sms hence though it is a contradiction method

        Call.creator(new PhoneNumber(notification.getPhoneNumber()),
                new PhoneNumber(FROM_NUMBER),
                        new URI("https://increate-unlethally-lee.ngrok-free.dev/api/v1/notification/ivr"))
                .create();
        log.info("Call made successfully to {}",notification.getPhoneNumber());
    }
}
