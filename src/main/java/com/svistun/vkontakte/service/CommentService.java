package com.svistun.vkontakte.service;

import com.svistun.vkontakte.dto.commentDto.request.PostCommentsDto;

public interface CommentService {
    void comments (PostCommentsDto postCommentsDto);
}
