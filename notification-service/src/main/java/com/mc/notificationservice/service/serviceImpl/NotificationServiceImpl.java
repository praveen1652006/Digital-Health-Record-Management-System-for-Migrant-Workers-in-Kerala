package com.mc.notificationservice.service.serviceImpl;

import com.mc.notificationservice.model.Notification;
import com.mc.notificationservice.notificationProvider.NotificationInterface;
import com.mc.notificationservice.notificationProvider.notificationProviderImpl.TwilioService;
import com.mc.notificationservice.repository.NotificationRepository;
import com.mc.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationInterface notificationInterface;

    private final NotificationRepository notificationRepository;


    public NotificationServiceImpl(@Qualifier("twilio")TwilioService twilioService,
                                   NotificationRepository notificationRepository) {
        this.notificationInterface = twilioService;
        this.notificationRepository = notificationRepository;
    }


    public void makeAnIVRCall(Notification notification) throws URISyntaxException {
        notificationInterface.makeIVRCall(notification);
    }
}
