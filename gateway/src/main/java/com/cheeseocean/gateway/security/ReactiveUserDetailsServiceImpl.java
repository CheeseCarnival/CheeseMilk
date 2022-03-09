package com.cheeseocean.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cheeseocean.common.entity.Role;
import com.cheeseocean.gateway.repository.RoleRepository;
import com.cheeseocean.gateway.repository.UserAuthRepository;

import reactor.core.publisher.Mono;

@Service
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService, ReactiveUserDetailsPasswordService {

    private UserAuthRepository userAuthRepository;

    private RoleRepository roleRepository;

    @Autowired
    public void setUserAuthRepository(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Mono<UserDetails> findByUsernameAndDetails(String username, Object details) {
        return userAuthRepository.findByIdentifyTypeAndIdentifier(((LoginAuthenticationDetails) details).getIdentifyType(), username)
                .map(userAuth -> User.builder().username(userAuth.getIdentifier())
                        .password(userAuth.getCredential()).roles(roleRepository.findRolesByUserId(userAuth.getUserInfoId()))).cast(UserDetails.class);
    }


    @Override
    public Mono<UserDetails> updatePassword(UserDetails user, String newPassword) {
        return null;
    }
}
