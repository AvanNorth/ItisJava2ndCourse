package tat.itis.chatsem.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tat.itis.chatsem.dto.ChatRoomDto;
import tat.itis.chatsem.models.ChatRoom;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ChatRoomsMapper {
    private final AccountsMapper accountsMapper;
    private final MessagesMapper messagesMapper;

    public ChatRoomDto toChatRoomDto(ChatRoom chatRoom) {
        return ChatRoomDto.builder()
                .id(chatRoom.getId())
                .participants(chatRoom.getParticipants().stream().map(accountsMapper::toAccountDto).collect(Collectors.toSet()))
                .messages(chatRoom.getMessages().stream().map(messagesMapper::toMessageDto).collect(Collectors.toList()))
                .build();
    }
}
