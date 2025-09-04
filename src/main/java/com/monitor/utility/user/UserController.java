package com.monitor.utility.user;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import com.monitor.utility.commons.ApiResponse;
import com.monitor.utility.commons.AuthRequest;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ApiResponse<String> authenticate(@RequestBody() AuthRequest request) {
        var token = userService.authenticate(request);
        return new ApiResponse<>(200,
                "U-200",
                "Login Successful",
                token);
    }
}
