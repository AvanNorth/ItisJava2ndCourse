package ru.itis.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMessageForm {
    @NotBlank(message = "BLANK_MESSAGE_BODY")
    private String body;
    private Long chatRoomId;
}
