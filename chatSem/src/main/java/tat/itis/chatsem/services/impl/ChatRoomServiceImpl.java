package tat.itis.chatsem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tat.itis.chatsem.dto.ChatRoomDto;
import tat.itis.chatsem.dto.CreateChatForm;
import tat.itis.chatsem.exceptions.ErrorEntity;
import tat.itis.chatsem.exceptions.ValidationException;
import tat.itis.chatsem.mapper.ChatRoomsMapper;
import tat.itis.chatsem.models.Account;
import tat.itis.chatsem.models.ChatRoom;
import tat.itis.chatsem.repositories.AccountsRepository;
import tat.itis.chatsem.repositories.ChatRepository;
import tat.itis.chatsem.services.ChatRoomService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRepository chatRepository;
    private final AccountsRepository accountsRepository;
    private final ChatRoomsMapper chatRoomsMapper;

    @Override
    public List<ChatRoomDto> getAllChats(Long accountId) {
        return chatRepository.findAllByParticipantsContaining(accountsRepository.getById(accountId))
                .stream()
                .map(chatRoomsMapper::toChatRoomDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ChatRoomDto createChatRoom(CreateChatForm createChatForm, Long accountId) {
        Set<Account> participants = new HashSet<>();

        participants.add(accountsRepository
                .findById(accountId)
                .orElseThrow((Supplier<RuntimeException>)
                        () -> new ValidationException(ErrorEntity.ACCOUNT_NOT_FOUND)));
        participants.add(accountsRepository
                .findById(createChatForm.getReceiverId())
                .orElseThrow((Supplier<RuntimeException>)
                        () -> new ValidationException(ErrorEntity.ACCOUNT_NOT_FOUND)));

        ChatRoom chatRoom = ChatRoom.builder()
                .participants(participants)
                .messages(new ArrayList<>())
                .build();

        return chatRoomsMapper.toChatRoomDto(chatRepository.save(chatRoom));
    }

    @Override
    public ChatRoomDto getChatRoom(Long chatId, Long accountId) {
        ChatRoom chatRoom = chatRepository
                .findById(chatId)
                .orElseThrow((Supplier<RuntimeException>)
                        () -> new ValidationException(ErrorEntity.CHAT_NOT_FOUND));
        if (chatRoom.getParticipants().contains(accountsRepository.getById(accountId))) {
            return  chatRoomsMapper.toChatRoomDto(chatRoom);
        } else {
            throw new ValidationException(ErrorEntity.ACCESS_TO_FOREIGN_DATA);
        }
    }

    @Override
    public void deleteChatRoom(Long chatId, Long accountId) {
        ChatRoom chatRoom = chatRepository
                .findById(chatId)
                .orElseThrow((Supplier<RuntimeException>)
                        () -> new ValidationException(ErrorEntity.CHAT_NOT_FOUND));
        if (chatRoom.getParticipants().contains(accountsRepository.getById(accountId))) {
            chatRepository.deleteById(chatId);
        } else {
            throw new ValidationException(ErrorEntity.ACCESS_TO_FOREIGN_DATA);
        }
    }
}
