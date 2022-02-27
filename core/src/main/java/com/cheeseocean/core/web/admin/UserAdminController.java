package com.cheeseocean.core.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class UserAdminController {


    @PostMapping(path = "/users/{username}/roles", params = "role")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addRole(@PathVariable String username, @RequestParam("role") String role)
    {
        
    }
}
