package com.cheeseocean.core.api;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.cheeseocean.common.model.OpResult;
import com.cheeseocean.common.page.PageResult;
import com.cheeseocean.core.api.model.UserDetails;

public interface UserService {

    UserDetails getUserInfoByUid(Long uid);

    UserDetails getUserInfoByUsername(String username);

    List<UserDetails> listUserByIds(List<Long> userIds);

    PageResult<UserDetails> listUsers(Pageable pageable);

    OpResult addOrUpdateUser(UserDetails newUser);

    OpResult deleteUserByUid(Long uid);

}
