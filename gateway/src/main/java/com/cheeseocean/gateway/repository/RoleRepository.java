package com.cheeseocean.gateway.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import com.cheeseocean.common.entity.Role;

import reactor.core.publisher.Mono;


@Repository
public class RoleRepository {

    @Autowired
    private DatabaseClient databaseClient;

    public Mono<List<Role>> findRolesByUserId(Long userId) {
        return databaseClient.sql("select " +
                        "role.id, role.name, role.created_at, role.updated_at, role.remark, role.permissions " +
                        "from tbl_role role left join tbl_user_roles tur on role.id = tur.roles_id" +
                        " where tur.user_id = " + userId)
                .fetch()
                .all().map(result -> Role.builder().name(result.get("name").toString())
                        .permissions(Integer.parseInt(result.get("permissions").toString()))
                        .build())
                .collectList();
    }

}
