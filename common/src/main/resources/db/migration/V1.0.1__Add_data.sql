insert into cheeseocean.tbl_user (id, username, nickname, email, gender, status, bio, avatar_url, remark)
values (1, 'xxxcrel', '克里尔', 'xxxcrel@gmail.com', 1, 1, 'Chaos is a ladder', 'https://oss.cheeseocean.com/avatar/1.png','nothing');

insert into cheeseocean.tbl_user_auth (id, bind_flag, identify_type, identifier, credential)
values (1, 1, 'username', 'xxxcrel', 'helloxc');