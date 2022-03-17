package com.svistun.vkontakte.controller;

import com.svistun.vkontakte.dto.likeDto.request.LikePostDto;
import com.svistun.vkontakte.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/like")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<?> likePost(@RequestBody LikePostDto likePostDto) {
        try {
            likeService.likePost(likePostDto);
            return new ResponseEntity<>("ok", HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
