package com.Friend.TaskMAster.service;


import com.Friend.TaskMAster.model.Invitation;
import org.springframework.stereotype.Service;

@Service
public class InvitationServiceImpl implements InvitationService {
    @Override
    public void sendInvitation(String email, long projectId) {

    }

    @Override
    public Invitation acceptInvitation(String token, long userId) {
        return null;
    }

    @Override
    public String getTokenByUserMail(String userEmail) {
        return "";
    }

    @Override
    public void deleteToken(String token) {

    }
}
