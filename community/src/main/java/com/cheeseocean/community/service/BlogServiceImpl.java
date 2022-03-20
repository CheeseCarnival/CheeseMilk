package com.cheeseocean.community.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.cheeseocean.community.api.BlogService;
import com.cheeseocean.community.api.model.BlogDetails;
import com.cheeseocean.community.entity.Blog;
import com.cheeseocean.community.repository.BlogRepository;
import com.cheeseocean.core.api.UserService;
import com.cheeseocean.core.api.model.UserDetails;

@Service
public class BlogServiceImpl implements BlogService {

    @DubboReference
    private UserService userService;

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Slice<BlogDetails> listBlog(Pageable pageable) {
        Page<Blog> rawBlogList = blogRepository.findAll(pageable);
//        return rawBlogList.map(BlogDetails);
        return null;
    }

    @Override
    public Slice<BlogDetails> listBlogByCategory(Long categoryId, Pageable pageable) {
        return null;
    }

    @Override
    public Slice<BlogDetails> listBlogByUser(Long userId, Pageable pageable) {
        return null;
    }

    @Override
    public BlogDetails getBlogDetails(Long blogId) {
        return null;
    }


    //    private final Log logger = LogFactory.getLog(getClass());
//    public static final String[] POSTVO_TO_POST_IGNORE = new String[]{"id", "likeCount", "commentCount", "createdAt",};
//    public static final String[] POST_TO_POSTVO_IGNORE = new String[]{""};
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    private StarRepository starRepository;
//
//    @Autowired
//    private AclManager aclManager;
//
//    @Autowired
//    @Qualifier("jdkFileService")
//    private FileService fileService;
//
//
//    @Transactional
//    public Page<PostVO> listPostsByUser(User currentUser, MultiDataQueryController.DateTuple queryPeriod, Pageable pageable) {
//        Page<Post> pagedPost = postRepository.getAllByUserAndCreatedAtAfterAndCreatedAtBefore(currentUser, queryPeriod.start, queryPeriod.end, pageable);
//        return pagedPost.map(CustomPostCopy::apply).map(postVO -> {
//            postVO.setStarred(starRepository.existsById(new Star.StarPK(currentUser, postVO.getId(), Star.ResourceType.POST.ordinal())));
//            return postVO;
//        });
////        return rawPostList.map(CustomPostCopy::apply);
//    }
//
//    /************ doesn't need authentication *****************/
//
//
//    @Transactional
//    public Page<PostVO> listPostsByUsername(String username, MultiDataQueryController.DateTuple queryPeriod, Pageable pageable) {
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("user: " + username + " not found"));
//
//        return listPostsByUser(user, queryPeriod, pageable);
//    }
//
//
//    public PostVO getPostByPid(Long pid) throws NotFoundException {
//        Post post = postRepository.findById(pid).orElseThrow(() -> new NotFoundException("post: " + pid + " not found"));
//        return CustomPostCopy.apply(post);
//    }
//
//
//    @Transactional
//    public Page<PostVO> listPostsByCategory(User user, String _category, MultiDataQueryController.DateTuple queryPeriod, Pageable pageable) {
//        Category category = categoryRepository.findByCategoryName(_category).orElseThrow(() -> new NotFoundException("category: " + _category + " not found"));
//        Page<Post> pagedPost = postRepository.getAllByCategoryAndCreatedAtAfterAndCreatedAtBefore(category, queryPeriod.start, queryPeriod.end, pageable);
//        if (user == null) {
//            return pagedPost.map(CustomPostCopy::apply);
//        }
//        return pagedPost.map(CustomPostCopy::apply).map(postVO -> {
//            postVO.setStarred(starRepository.existsById(new Star.StarPK(user, postVO.getId(), Star.ResourceType.POST.ordinal())));
//            return postVO;
//        });
//    }
//
//    /*****************  need authentication *******************/
//
//    public void postPlainBubble(User currentUser, PostDTO postDTO) {
//
//    }
//
//    public void removeBubble(User operateUser, Long postID) {
//
//    }
//
//    @Transactional
//    public void postFlexBubble(User currentUser, PostDTO postDTO, List<MultipartFile> images) {
//        //transfer bubble content to persistent object
//        Post po = Post.builder().fromDTO(postDTO);
//        po.setUser(currentUser);
//        po.setCategory(categoryRepository.findByCategoryName(postDTO.getCategory()).orElseThrow(() -> new NotFoundException("category name not found")));
//
//        //add bubble images
//        if (!images.isEmpty()) {
//            images.forEach(image -> {
//                Assert.hasText(image.getOriginalFilename());
//            });
//            po.setImages(url2Image(images.stream()
//                    .map(MultipartFile::getOriginalFilename)
//                    .collect(Collectors.toList())));
//            fileService.uploadFiles(AppConstants.BUBBLE_IMAGE_PATH, images.stream().collect(Collectors.toMap(MultipartFile::getOriginalFilename, image -> image)));
//        }
//
//        // save
//        postRepository.save(po);
//
//        // add permission on post object
//        aclManager.addPermission(AclDTO.builder().securedObject(po).role(po.getCategory().getCategoryName()).permission(BasePermission.DELETE).build());
//
//    }
//
//    @Transactional
//    public void starPost(User currentUser, Long postId) {
//        Assert.notNull(currentUser, "user must be authenticated");
//        Assert.isTrue(postRepository.existsById(postId), "post id doesn't exist");
//
//        Star star = new Star();
//        star.setStarPK(new Star.StarPK(currentUser, postId, Star.ResourceType.POST.ordinal()));
//        starRepository.save(star);
//        postRepository.updateStars(postId, 1);
//    }
//
//    @Transactional
//    public void unstarPost(User currentUser, Long postId) {
//        Assert.notNull(currentUser, "user must be authenticated");
//        Assert.isTrue(postRepository.existsById(postId), "post id doesn't exist");
//
//        Star star = new Star();
//        star.setStarPK(new Star.StarPK(currentUser, postId, Star.ResourceType.POST.ordinal()));
//        starRepository.delete(star);
//        postRepository.updateStars(postId, -1);
//    }
//
//    private Set<Image> url2Image(List<String> filenames) {
//        HashSet<Image> set = new HashSet<>();
//        String prefix = AppConstants.STATIC_SERVER_PREFIX;
//        String path = AppConstants.BUBBLE_IMAGE_PATH;
//        filenames.forEach(filename -> {
//            String imageUrl = prefix.concat(path).concat(filename);
//            logger.info("bubble image: " + imageUrl);
//            set.add(new Image(imageUrl));
//        });
//        return set;
//    }
//

}
