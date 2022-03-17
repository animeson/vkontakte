package com.svistun.vkontakte.dto.commentDto.request;

import lombok.Data;

@Data
public class PostCommentsDto {
    private Long postId;
    private String comments;
    private Long authorId;

}
