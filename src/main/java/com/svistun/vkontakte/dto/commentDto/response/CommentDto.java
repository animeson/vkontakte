package com.svistun.vkontakte.dto.commentDto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long authorId;
    private LocalDateTime dateTime;
    private String comments;
}
