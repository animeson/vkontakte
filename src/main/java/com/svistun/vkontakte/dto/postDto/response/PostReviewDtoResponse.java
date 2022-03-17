package com.svistun.vkontakte.dto.postDto.response;

import com.svistun.vkontakte.dto.commentDto.request.PostCommentsDto;
import com.svistun.vkontakte.dto.commentDto.response.CommentDto;
import com.svistun.vkontakte.dto.likeDto.request.LikePostDto;
import com.svistun.vkontakte.dto.userDto.response.UserInfoPostDto;
import com.svistun.vkontakte.entity.MediaFile;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostReviewDtoResponse {
    private Long id;
    private UserInfoPostDto creator;
    private MediaFile media;
    private String description;
    private List<LikePostDto> likes;
    private LocalDateTime date;
    private List<CommentDto> comments;
}
