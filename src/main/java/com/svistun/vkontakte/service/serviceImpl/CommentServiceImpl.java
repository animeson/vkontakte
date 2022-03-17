package com.svistun.vkontakte.service.serviceImpl;

import com.svistun.vkontakte.dto.commentDto.request.PostCommentsDto;
import com.svistun.vkontakte.entity.Comments;
import com.svistun.vkontakte.repository.CommentRepository;
import com.svistun.vkontakte.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Transactional
    @Override
    public void comments(PostCommentsDto postCommentsDto) {
        Comments comments = new Comments();
        BeanUtils.copyProperties(comments, postCommentsDto);
        comments.setDate(LocalDateTime.now());
        commentRepository.save(comments);


    }

}
