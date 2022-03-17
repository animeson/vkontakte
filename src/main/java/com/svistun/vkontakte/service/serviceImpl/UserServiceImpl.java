package com.svistun.vkontakte.service.serviceImpl;

import com.svistun.vkontakte.dto.userDto.response.UserInfoDto;
import com.svistun.vkontakte.entity.User;
import com.svistun.vkontakte.repository.UserRepository;
import com.svistun.vkontakte.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserInfoDto getUserById() {
        User user = userRepository.getById(1L);
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(user,userInfoDto);
        return userInfoDto;
    }



}
