package com.Friend.TaskMAster.service;

import com.Friend.TaskMAster.model.Chat;
import com.Friend.TaskMAster.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl  implements ChatService {


    private ChatRepository chatRepository;

    @Override
    public Chat createChat(Chat chat) {


        return chatRepository.save(chat);
    }
}
