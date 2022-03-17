package com.svistun.vkontakte.dto.postDto.request;

import lombok.Data;
@Data
public class CreatePostDto {
    private String patchMediaFile;
    private Long creatorId;
    private String description;
}
