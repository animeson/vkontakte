package com.svistun.vkontakte.repository;

import com.svistun.vkontakte.entity.LikeMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<LikeMark, Long> {
    List<LikeMark> getByPostId(Long post_id);
}
