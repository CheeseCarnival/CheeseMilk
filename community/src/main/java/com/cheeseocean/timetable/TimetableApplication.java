package com.cheeseocean.timetable;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;

public class TimetableApplication {
    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisStringCommands sync = connection.sync();
        String value = (String) sync.get("key");
        System.out.println(value);
    }
}
