package com.svistun.vkontakte.dto.messageDto.response;

import com.svistun.vkontakte.dto.userDto.response.MessageUserInfoDto;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MessageReadDto {
    private String message;
    private LocalDateTime dataTimeSendingMessages;
    private MessageUserInfoDto senderById;
    private MessageUserInfoDto recipientById;
}
