package com.svistun.vkontakte.controller;

import com.svistun.vkontakte.dto.postDto.request.CreatePostDto;
import com.svistun.vkontakte.dto.likeDto.request.LikePostDto;
import com.svistun.vkontakte.dto.commentDto.request.PostCommentsDto;
import com.svistun.vkontakte.dto.postDto.request.PostReviewDto;
import com.svistun.vkontakte.service.PostService;
import com.svistun.vkontakte.service.serviceImpl.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody CreatePostDto createPostDto) {
        try {
            postService.createPost(createPostDto);
            return ResponseEntity.ok("Ok");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> reviewPost(@RequestBody PostReviewDto postReviewDto) {
        try {
            return new ResponseEntity<>(postService.reviewPost(postReviewDto), HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

}
