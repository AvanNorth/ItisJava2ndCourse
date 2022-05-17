package tat.itis.chatsem.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomDto {

    private Long id;

    private Set<AccountDto> participants;

    private List<MessageDto> messages;

}
