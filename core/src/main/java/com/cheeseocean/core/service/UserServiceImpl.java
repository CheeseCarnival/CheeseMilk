package com.cheeseocean.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cheeseocean.common.model.OpResult;
import com.cheeseocean.common.page.PageResult;
import com.cheeseocean.core.api.UserService;
import com.cheeseocean.core.api.model.UserDetails;
import com.cheeseocean.core.entity.UserInfo;
import com.cheeseocean.core.repository.UserRepository;

@Service
@DubboService
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDetails> listUserByIds(List<Long> userIds) {
        return userRepository.findAllById(userIds)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }


    @Override
    public UserDetails getUserInfoByUid(Long uid) {
        return null;
    }

    @Override
    public UserDetails getUserInfoByUsername(String username) {
        return null;
    }

    @Override
    public PageResult<UserDetails> listUsers(Pageable pageable) {
        return null;
    }

    @Override
    public OpResult addOrUpdateUser(UserDetails newUser) {
        return null;
    }

    @Override
    public OpResult deleteUserByUid(Long uid) {
        return null;
    }

    public UserDetails from(UserInfo userInfo){
        return UserDetails.newBuilder()
                .uid(userInfo.getId())
                .username(userInfo.getUsername())
                .nickname(userInfo.getNickname())
                .avatarUrl(userInfo.getAvatarUrl())
                .location(userInfo.getLocation())
                .bio(userInfo.getBio())
                .gender(userInfo.getGender())
                .email(userInfo.getEmail())
                .status(userInfo.getStatus().name())
                .build();
    }

}
