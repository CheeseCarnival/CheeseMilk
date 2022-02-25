package com.cheeseocean.niot.api;

import com.cheeseocean.common.annotation.CurrentUser;
import com.cheeseocean.common.entity.User;
import com.cheeseocean.niot.constant.JwtConstants;
import com.cheeseocean.common.web.response.Result;
import com.cheeseocean.niot.service.FileService;
import com.cheeseocean.niot.service.MailService;
import com.cheeseocean.niot.service.PostService;
import com.cheeseocean.niot.service.UserService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

import java.util.List;


@RestController
@RequestMapping
@Validated
@SessionAttributes(value = "verifyDTO")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private StarRepository starRepository;

    @Autowired
    @Qualifier("jdkFileService")
    private FileService fileService;

    @Autowired
    private MailService mailService;

    @PostMapping(value = "/login", params = {"username", "password"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Result<String> login(@RequestParam("username")@NotEmpty String username,
                                @RequestParam("password")@NotEmpty String password, HttpServletResponse response){
        String jwtToken = userService.login(username, password);
        response.setHeader(HttpHeaders.AUTHORIZATION, JwtConstants.TOKEN_SCHEMA + jwtToken);
        return Result.ok("login successful");
    }

    @PostMapping(path = "/user/profile")
    public Result<String> uploadAvatar(@CurrentUser User currentUser , @RequestPart("update_avatar")@Validated MultipartFile avatar){
        userService.uploadAvatar(currentUser, avatar);
        return Result.ok("update avatar successful!");
    }

    @PostMapping(path = "/user/profile", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Result<String> updateNickname(@CurrentUser User currentUser, @RequestBody @Validated UserUpdateDTO updateDTO) {
        userService.updateUserProfile(currentUser, updateDTO.getUpdatedField(), updateDTO.getUpdatedValue());
        return Result.ok("update field: " + updateDTO.getUpdatedField() + " successful");
    }

    /// 把发帖放在user下是为了显示发帖这一动作只属于用户
    @PostMapping("/user/posts")
    @ResponseStatus(HttpStatus.OK)
    public Result<String> postBubble(@CurrentUser User user, @RequestPart("meta-data") PostDTO postDTO, @RequestPart(value = "images[]", required = false) List<MultipartFile> images) {

        postService.postFlexBubble(user, postDTO, images);
        return Result.ok("post successful");
    }

    @GetMapping("/user/profile")
    @ResponseStatus(HttpStatus.OK)
    public UserVO getCurrentUser(@CurrentUser User currentUser){
        UserVO result = new UserVO();
        BeanUtils.copyProperties(currentUser, result);
        return result;
    }

    @PostMapping("/user/timetable")
    @ResponseStatus(HttpStatus.OK)
    public Result<String> uploadTimetable(@CurrentUser User user,
                                @RequestBody List<CourseDTO> courses){
        userService.uploadTimetable(user, courses);
        return Result.ok("上传课程表成功");
    }

    @GetMapping("/user/timetable")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseDTO> getTimetable(@CurrentUser User user){
        return userService.getTimetableByUser(user);
    }
}
