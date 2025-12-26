package com.mc.notificationservice.notificationProvider;

import com.mc.notificationservice.model.Notification;

import java.net.URISyntaxException;

public interface NotificationInterface {
    void makeIVRCall(Notification notification) throws URISyntaxException;
}
