package com.mc.notificationservice.service;

import com.mc.notificationservice.model.Notification;

import java.net.URISyntaxException;

public interface NotificationService {

    void makeAnIVRCall(Notification notification) throws URISyntaxException;
}
