package com.svistun.vkontakte.controller;

import com.svistun.vkontakte.dto.messageDto.request.MessageSendRequestDto;
import com.svistun.vkontakte.dto.messageDto.request.MessageViewerSenderDto;
import com.svistun.vkontakte.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {
    private final MessageService messageService;


    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @PostMapping
    private ResponseEntity<?> sentUserMessage(@RequestBody MessageSendRequestDto messageSendRequestDto) {
        try {
            messageService.sendMessage(messageSendRequestDto);
            return new ResponseEntity<>("sent message", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping
    private ResponseEntity<?> getUserMessage(@RequestBody MessageViewerSenderDto messageViewerSenderDto) {
        try {
            return new ResponseEntity<>(messageService.viewerMessage(messageViewerSenderDto),HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

}
