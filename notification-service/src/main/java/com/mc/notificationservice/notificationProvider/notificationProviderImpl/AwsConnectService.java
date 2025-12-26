package com.mc.notificationservice.notificationProvider.notificationProviderImpl;

import com.mc.notificationservice.model.Notification;
import com.mc.notificationservice.notificationProvider.NotificationInterface;
import org.springframework.stereotype.Service;

@Service
public class AwsConnectService implements NotificationInterface {
    @Override
    public void makeIVRCall(Notification notification) {

    }
}
