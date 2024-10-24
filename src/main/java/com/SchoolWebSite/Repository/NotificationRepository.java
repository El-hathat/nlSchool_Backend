package com.SchoolWebSite.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SchoolWebSite.Models.Notification;
import com.SchoolWebSite.Models.Student;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStudentOrderByMessageDateDesc(Student student);
    List<Notification> findByStudentAndReadingOrderByMessageDateDesc(Student student,boolean reading);
    Notification getNotificationById(Long id);
    
}

