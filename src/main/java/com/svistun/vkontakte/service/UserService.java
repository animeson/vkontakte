package com.svistun.vkontakte.service;

import com.svistun.vkontakte.dto.userDto.response.UserInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  UserInfoDto getUserById();
}
