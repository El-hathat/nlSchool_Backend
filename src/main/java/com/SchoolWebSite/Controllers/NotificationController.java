package com.SchoolWebSite.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SchoolWebSite.Models.Notification;
import com.SchoolWebSite.Services.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{studentId}")
    public List<Notification> getStudentNotifications(@PathVariable String studentId) {
        return notificationService.getNotificationsByStudent(studentId);
    }
    
    @GetMapping("/isreading/{studentId}")
    public int getStudentNotificationIsreading(@PathVariable String studentId) {
        return notificationService.getNotificationsByStudentIsReadinf(studentId).size();
    }
    
    @GetMapping("/add/{email}")
    @ResponseBody
    public boolean addNtf(@PathVariable String email) {
        return notificationService.updatenotification(email);
    }
}

