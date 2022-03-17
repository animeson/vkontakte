package com.svistun.vkontakte.repository;

import com.svistun.vkontakte.entity.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaFileRepository extends JpaRepository<MediaFile,Long> {
    MediaFile getByName(String name);
    MediaFile getByHashFile(Integer hashFile);
}
