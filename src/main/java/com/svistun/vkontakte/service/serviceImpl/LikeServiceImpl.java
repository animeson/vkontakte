package com.svistun.vkontakte.service.serviceImpl;

import com.svistun.vkontakte.dto.commentDto.request.PostCommentsDto;
import com.svistun.vkontakte.dto.commentDto.response.CommentDto;
import com.svistun.vkontakte.dto.likeDto.request.LikePostDto;
import com.svistun.vkontakte.dto.postDto.request.CreatePostDto;
import com.svistun.vkontakte.dto.postDto.request.PostReviewDto;
import com.svistun.vkontakte.dto.postDto.response.PostReviewDtoResponse;
import com.svistun.vkontakte.dto.userDto.response.UserInfoPostDto;
import com.svistun.vkontakte.entity.Comments;
import com.svistun.vkontakte.entity.LikeMark;
import com.svistun.vkontakte.entity.MediaFile;
import com.svistun.vkontakte.entity.Post;
import com.svistun.vkontakte.repository.CommentRepository;
import com.svistun.vkontakte.repository.LikeRepository;
import com.svistun.vkontakte.repository.MediaFileRepository;
import com.svistun.vkontakte.repository.PostRepository;
import com.svistun.vkontakte.service.LikeService;
import com.svistun.vkontakte.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;


    public LikeServiceImpl( LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    @Transactional
    public void likePost(LikePostDto likePostDto) {
        LikeMark likeMark = new LikeMark();
        BeanUtils.copyProperties(likePostDto, likeMark);
        likeRepository.save(likeMark);

    }
}
