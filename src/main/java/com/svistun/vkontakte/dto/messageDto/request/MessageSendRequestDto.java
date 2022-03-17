package com.svistun.vkontakte.dto.messageDto.request;

import lombok.Data;

@Data
public class MessageSendRequestDto {
    private String message;
    private Long recipientById;
    private Long senderById;

}
