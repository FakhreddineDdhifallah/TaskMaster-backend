package com.Friend.TaskMAster.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendEmailWithToken(String userEmail, String link);
}
