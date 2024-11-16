package com.Friend.TaskMAster.repository;

import com.Friend.TaskMAster.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation,Long> {

    Invitation findByToken(String token);
    Invitation findByEmail(String userEmail);
    

}
