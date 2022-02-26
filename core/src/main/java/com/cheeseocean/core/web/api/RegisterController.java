package com.cheeseocean.core.web.api;

public class RegisterController {

//    public final Log logger = LogFactory.getLog(getClass());
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private MailService mailService;
//
//    @PostMapping(value = "/check", consumes = MediaType.TEXT_PLAIN_VALUE)
//    public void check(@RequestBody String s) throws IOException {
////        System.out.println(request.getInputStream().read());
//        System.out.println("check");
//        System.out.println(s);
//    }
//
//    @GetMapping(value = "/check", params = "username")
//    @ResponseStatus(HttpStatus.OK)
//    public void usernameCheck(@RequestParam("username")String username){
//        if(userService.presentByUsername(username))
//            throw new AlreadyExistsException("user:" + username + " already exists");
//    }
//
//    @GetMapping(value = "/code/email", params = "address")
//    @ResponseStatus(HttpStatus.OK)
//    public Result<String> verifyEmail(@RequestParam("address")@Email String email, Model model, HttpSession session){
//        if(userService.presentByEmail(email))
//            throw new AlreadyExistsException("email already registered");
//
//        String verifyCode = VerifyCodeGenerator.generate(6);
//        model.addAttribute("verifyDTO", new UserRegisterDTO.VerifyDTO(verifyCode, LocalDateTime.now()));
//        mailService.sendMail(email, verifyCode);
//
//        return Result.ok("verify email already send to: " + email);
//    }
//
//    @PostMapping("/register")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Result<String> register(@Valid UserRegisterDTO registerDTO,
//                                   @SessionAttribute(value = "verifyDTO") UserRegisterDTO.VerifyDTO verifyDTO,
//                                   SessionStatus sessionStatus) {
//        if(!verifyDTO.check(registerDTO.getCode(), LocalDateTime.now()))
//            return new Result<>("Time out, please resend email verification");
//        sessionStatus.setComplete();
//        logger.info("register dto: " + registerDTO);
//        userService.register(registerDTO);
//        return Result.ok("register successful");
//    }
}
