package com.Friend.TaskMAster.service;


import com.Friend.TaskMAster.model.Invitation;

public interface InvitationService {

    public void sendInvitation(String email , long projectId);
    public Invitation acceptInvitation(String token , long userId);


    public String getTokenByUserMail(String userEmail);

    void deleteToken(String token);
}
