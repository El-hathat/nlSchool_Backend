package com.SchoolWebSite.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SchoolWebSite.Models.Notification;
import com.SchoolWebSite.Repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    
    @Autowired
    StudentService std;

    public List<Notification> getNotificationsByStudent(String student) {
        return notificationRepository.findByStudentOrderByMessageDateDesc(std.getByEmail(student));
    }
    
    public List<Notification> getNotificationsByStudentIsReadinf(String student) {
        return notificationRepository.findByStudentAndReadingOrderByMessageDateDesc(std.getByEmail(student),false);
    }
    
    public boolean updatenotification(String email) {
    	for (Notification notif : notificationRepository.findByStudentAndReadingOrderByMessageDateDesc(std.getByEmail(email),false)) {
			notif.setReading(true);
			notificationRepository.save(notif);
		}
        return notificationRepository.findByStudentAndReadingOrderByMessageDateDesc(std.getByEmail(email),false).size()==0;
    }
}

