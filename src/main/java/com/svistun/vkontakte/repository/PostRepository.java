package com.svistun.vkontakte.repository;

import com.svistun.vkontakte.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> getPostByCreatorId(Long creatorId);


}
