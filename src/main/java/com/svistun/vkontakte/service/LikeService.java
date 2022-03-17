package com.svistun.vkontakte.service;

import com.svistun.vkontakte.dto.likeDto.request.LikePostDto;
import com.svistun.vkontakte.dto.messageDto.request.MessageSendRequestDto;
import com.svistun.vkontakte.dto.messageDto.request.MessageViewerSenderDto;
import com.svistun.vkontakte.dto.messageDto.response.MessageReadDto;

import java.util.List;

public interface LikeService {
    void likePost(LikePostDto likePostDto);
}
