package tat.itis.chatsem.services;

import tat.itis.chatsem.dto.ChatRoomDto;
import tat.itis.chatsem.dto.CreateChatForm;

import java.util.List;

public interface ChatRoomService {
    List<ChatRoomDto> getAllChats(Long accountId);
    ChatRoomDto createChatRoom(CreateChatForm createChatForm, Long accountId);
    ChatRoomDto getChatRoom(Long chatId, Long accountId);
    void deleteChatRoom(Long chatId, Long accountId);
}
