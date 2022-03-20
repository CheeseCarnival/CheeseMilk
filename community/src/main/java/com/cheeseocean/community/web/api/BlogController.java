package com.cheeseocean.community.web.api;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheeseocean.common.web.response.Result;
import com.cheeseocean.community.api.BlogService;
import com.cheeseocean.community.api.model.BlogDetails;
import com.cheeseocean.community.web.response.BlogVO;
import com.cheeseocean.core.api.UserService;
import com.cheeseocean.core.api.model.UserDetails;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @DubboReference
    private UserService userService;

    @GetMapping("/list")
    public Result<?> listBlog(@PageableDefault Pageable pageable){
        Slice<BlogDetails> blogs = blogService.listBlog(pageable);
        List<Long> uids = blogs.getContent().stream().map(BlogDetails::getUid).collect(Collectors.toList());
        List<UserDetails> users = userService.listUserByIds(uids);
        return Result.ok(blogs.map(blog -> {
            BlogVO vo = new BlogVO();
            vo.setBlogInfo(blog);
            for (UserDetails user : users) {
                if (user.getUid().equals(blog.getUid()))
                    vo.setBlogInfo(blog);
            }
            return vo;
        }));
    }

//
//    @Autowired
//    private PostService postService;
//
//    @Autowired
//    private CommentService commentService;
//
//
//    @PostMapping(value = "/user/posts")
//    public Result publishPost(@RequestParam("category") String category,
//                              @RequestPart("meta-data") Map<String, String> data,
//                              @RequestPart("images[]") List<MultipartFile> images) {
//
//    }
//
//
//    @PostMapping(value = "/posts/{postID}/comments/")
//    @ResponseStatus(HttpStatus.OK)
//    public Result<String> addComment(@CurrentUser User user,
//                                     @PathVariable long postID,
//                                     @RequestPart("meta-data") CommentDTO commentDTO) {
//
//        commentService.addComment(user, postID, commentDTO);
//
//        return Result.ok("comment successful");
//    }
//
//    @GetMapping(value = "/posts/{postID}/stars")
//    @ResponseStatus(HttpStatus.OK)
//    public void starPost(@CurrentUser User user, @PathVariable Long postID) {
//        postService.starPost(user, postID);
//    }
//
//    @DeleteMapping(value = "/posts/{postID}/stars")
//    public void unstarPost(@CurrentUser User user, @PathVariable Long postID) {
//        postService.unstarPost(user, postID);
//    }
//
//    @DeleteMapping("/posts/{postID}")
//    @ResponseStatus(HttpStatus.OK)
//    public Result<String> removeBubble(@CurrentUser User user, @PathVariable Long postID) {
//
//        postService.removeBubble(user, postID);
//
//        return Result.ok("remove post" + postID + " successful");
//    }
}
