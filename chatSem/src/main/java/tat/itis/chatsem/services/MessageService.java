package tat.itis.chatsem.services;

import tat.itis.chatsem.dto.ChatRoomDto;
import tat.itis.chatsem.dto.CreateMessageForm;
import tat.itis.chatsem.dto.MessageDto;

public interface MessageService {
    ChatRoomDto sendMessage(CreateMessageForm createMessageForm, Long authorId);
    ChatRoomDto editMessage(MessageDto messageDto, Long accountId);

    void deleteMessage(Long messageId, Long accountId);
}
