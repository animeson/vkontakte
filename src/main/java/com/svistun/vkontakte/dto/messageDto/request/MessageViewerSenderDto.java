package com.svistun.vkontakte.dto.messageDto.request;

import lombok.Data;

@Data
public class MessageViewerSenderDto {
    private Long senderById;
    private Long recipientById;
}
