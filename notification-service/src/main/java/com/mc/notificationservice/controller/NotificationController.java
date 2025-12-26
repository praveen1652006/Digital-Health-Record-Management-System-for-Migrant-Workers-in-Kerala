package com.mc.notificationservice.controller;

import com.mc.notificationservice.model.Notification;
import com.mc.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<String> callPatient(@RequestBody Notification notification) throws URISyntaxException {
        notificationService.makeAnIVRCall(notification);
        return ResponseEntity.status(HttpStatus.OK).body("Message Sent Successfully to Patient: "
                +notification.getPatientName());
    }

    @PostMapping(value = "/ivr", produces = "application/xml")
    public String ivrResponse() {
        return """
        <Response>
            <Say voice="alice">Hello, this is a reminder call.</Say>
            <Pause length="1"/>
            <Gather numDigits="1" action="/ivr/response">
                <Say>Press 1 if you will visit. Press 2 to reschedule. Press 3 if you cannot come.</Say>
            </Gather>
        </Response>
        """;
    }

}
