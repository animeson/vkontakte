package com.svistun.vkontakte.repository;

import com.svistun.vkontakte.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> getByPostId(Long id);

}
