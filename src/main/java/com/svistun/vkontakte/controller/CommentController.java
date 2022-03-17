package com.svistun.vkontakte.controller;

import com.svistun.vkontakte.dto.commentDto.request.PostCommentsDto;
import com.svistun.vkontakte.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/like")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody PostCommentsDto postCommentsDto) {
        try {
            commentService.comments(postCommentsDto);
            return new ResponseEntity<>("ok", HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
