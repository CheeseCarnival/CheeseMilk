package com.cheeseocean.core.api;

import java.util.List;

import com.cheeseocean.core.api.model.UserDetails;

public interface UserService {

    List<UserDetails> listUserByIds(List<Long> userIds);
}
