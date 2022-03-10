package com.cheeseocean.gateway.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;


@Repository
public class UserAuthRepository {

    @Autowired
    private DatabaseClient databaseClient;

    public Mono<List<UserAuthWithRole>> findUserDetailsByIdentifierAndType(String username, String type) {
        //FIXME: it's a R2DBC SPI Bug, will return in alphabetical order, https://github.com/spring-projects/spring-data-r2dbc/issues/478
        return databaseClient.sql("select ua.credential password, role.name role_name, ua.identifier username " +
                        "from tbl_user_auth ua left join tbl_user_role ur on ua.user_id = ur.user_id left join tbl_role role on role.id = ur.role_id " +
                        "where ua.identify_type = '" + type + "' and ua.identifier = '" + username + "'")
                .fetch()
                .all()
                .switchIfEmpty(Mono.defer(() -> Mono.error(new UsernameNotFoundException("identifier not found: " + username))))
                .mapNotNull(result -> new UserAuthWithRole(result.get("username").toString(), result.get("password").toString(), result.get("role_name").toString()))
                .collectList();
    }

}
