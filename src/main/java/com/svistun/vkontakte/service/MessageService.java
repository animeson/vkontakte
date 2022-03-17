package com.svistun.vkontakte.service;

import com.svistun.vkontakte.dto.messageDto.request.MessageSendRequestDto;
import com.svistun.vkontakte.dto.messageDto.request.MessageViewerSenderDto;
import com.svistun.vkontakte.dto.messageDto.response.MessageReadDto;
import com.svistun.vkontakte.entity.Message;

import java.util.List;

public interface MessageService {
    void sendMessage(MessageSendRequestDto messageSendRequestDto);
    List<MessageReadDto> viewerMessage(MessageViewerSenderDto messageViewerSenderDto);
}
