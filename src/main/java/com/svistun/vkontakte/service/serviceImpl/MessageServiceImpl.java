package com.svistun.vkontakte.service.serviceImpl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.svistun.vkontakte.dto.messageDto.request.MessageSendRequestDto;
import com.svistun.vkontakte.dto.messageDto.request.MessageViewerSenderDto;
import com.svistun.vkontakte.dto.messageDto.response.MessageReadDto;
import com.svistun.vkontakte.dto.userDto.response.MessageUserInfoDto;
import com.svistun.vkontakte.entity.Message;
import com.svistun.vkontakte.repository.MessageRepository;
import com.svistun.vkontakte.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    @Override
    @Transactional
    public void sendMessage(MessageSendRequestDto messageSendRequestDto) {
        System.out.println(messageSendRequestDto);
        Message message = new Message();
        BeanUtils.copyProperties(messageSendRequestDto, message);
        message.setDataTimeSendingMessages(LocalDateTime.now());
        messageRepository.save(message);
    }

    @Override
    public List<MessageReadDto> viewerMessage(MessageViewerSenderDto messageViewerSenderDto) {
        List<Message> messageList = messageRepository.getMessagesBySenderByIdOrRecipientById(messageViewerSenderDto.getSenderById(),
                messageViewerSenderDto.getRecipientById());
        List<MessageReadDto> messageListReadDto = new ArrayList<>();
        MessageReadDto messageReadDto = new MessageReadDto();

        for (Message infoMessage : messageList) {
            messageReadDto.setMessage(infoMessage.getMessage());
            messageReadDto.setDataTimeSendingMessages(infoMessage.getDataTimeSendingMessages());

            MessageUserInfoDto messagesRecipientInfoDto = new MessageUserInfoDto();
            messagesRecipientInfoDto.setId(infoMessage.getRecipient().getId());
            messagesRecipientInfoDto.setFirstName(infoMessage.getRecipient().getFirstName());
            messagesRecipientInfoDto.setLastName(infoMessage.getRecipient().getLastName());

            messageReadDto.setRecipientById(messagesRecipientInfoDto);

            MessageUserInfoDto messagesSenderByIdInfoDto = new MessageUserInfoDto();
            messagesSenderByIdInfoDto.setId(infoMessage.getSender().getId());
            messagesSenderByIdInfoDto.setFirstName(infoMessage.getSender().getFirstName());
            messagesSenderByIdInfoDto.setLastName(infoMessage.getSender().getLastName());

            messageReadDto.setSenderById(messagesSenderByIdInfoDto);
            messageListReadDto.add(messageReadDto);
        }


        return messageListReadDto;
    }


}
