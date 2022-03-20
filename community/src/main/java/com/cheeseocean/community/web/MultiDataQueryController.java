package com.cheeseocean.community.web;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiDataQueryController {

//    private static final LocalDateTime farPastDate = LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0);
//    private final Log logger = LogFactory.getLog(getClass());
//    @Autowired
//    PostService postService;
//    @Autowired
//    CommentService commentService;
//
//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        binder.addCustomFormatter(new Formatter<LocalDateTime>() {
//            @Override
//            public LocalDateTime parse(String text, Locale locale) throws ParseException {
//                return Instant.ofEpochMilli(Long.parseLong(text)).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
//            }
//
//            @Override
//            public String print(LocalDateTime object, Locale locale) {
//                return object.toString();
//            }
//        });
//    }
//
//    @ModelAttribute("queryPeriod")
//    public DateTuple populateDuration(@RequestParam(value = "start", required = false) @DateTimeFormat LocalDateTime start,
//                                      @RequestParam(value = "end", required = false) @DateTimeFormat LocalDateTime end) {
//
//        LocalDateTime startDate = Optional.ofNullable(start).orElse(farPastDate);
////        logger.error(startDate.toInstant(ZoneOffset.of("+8")).toEpochMilli());
//        LocalDateTime endDate = Optional.ofNullable(end).orElse(LocalDateTime.now());
//        if (!endDate.isAfter(startDate)) {
//            throw new InvalidParameterException("end date time must be great than start date time, but start time: [" + startDate.toString() + "], after time: [" + endDate.toString() + "]");
//        }
//        return new DateTuple(startDate, endDate);
//    }
//
//    @GetMapping(value = "/categories/{category}/posts")
//    public Page<Result<?>> listPosts(@CurrentUser User user,
//                                     @PathVariable String category,
//                                     @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
//                                     @ModelAttribute("queryPeriod") DateTuple queryPeriod) {
//        return postService.listPostsByCategory(user, category, queryPeriod, pageable);
//    }
//
//    @GetMapping(value = "/user/posts", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Page<PostVO> getUserBubbleList(@CurrentUser User user,
//                                          @PageableDefault(size = 3, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
//                                          @ModelAttribute("queryPeriod") DateTuple queryPeriod) {
//        return postService.listPostsByUser(user, queryPeriod, pageable);
//    }
//
//    @GetMapping(value = "/posts/{postID}/comments")
//    public Page<CommentVO> getBubbleCommentList(@PathVariable Long postID,
//                                                @RequestParam(name = "level", defaultValue = "first", required = false) String level,
//                                                @RequestParam(name = "parent_id", required = false) Long parentId,
//                                                @PageableDefault Pageable pageable,
//                                                @ModelAttribute("queryPeriod") DateTuple queryPeriod) {
//
//        if (level.equals("first")) {
//            //if it's first level comment, ignore parent_id, search by post id
//            return commentService.listCommentsByPost(postID, queryPeriod, pageable);
//        } else if (level.equals("second") && parentId != null) {
//            return commentService.listCommentsByParent(parentId, queryPeriod, pageable);
//        }
//        throw new InvalidParameterException("invalid parameter: level=" + level + "&parent_id=" + (parentId == null ? "null" : parentId));
//    }
//
//    public static class DateTuple {
//        public LocalDateTime start;
//        public LocalDateTime end;
//
//        public DateTuple(LocalDateTime start, LocalDateTime end) {
//            this.start = start;
//            this.end = end;
//        }
//    }
}
