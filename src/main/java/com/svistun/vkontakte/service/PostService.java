package com.svistun.vkontakte.service;

import com.svistun.vkontakte.dto.postDto.request.CreatePostDto;
import com.svistun.vkontakte.dto.likeDto.request.LikePostDto;
import com.svistun.vkontakte.dto.commentDto.request.PostCommentsDto;
import com.svistun.vkontakte.dto.postDto.request.PostReviewDto;
import com.svistun.vkontakte.dto.postDto.response.PostReviewDtoResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface PostService {
    void createPost(CreatePostDto createPostDto) throws IOException;
    List<PostReviewDtoResponse> reviewPost (PostReviewDto messageReviewDto);



}
