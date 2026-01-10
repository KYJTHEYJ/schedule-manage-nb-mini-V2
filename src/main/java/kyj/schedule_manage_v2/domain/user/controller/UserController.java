package kyj.schedule_manage_v2.domain.user.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kyj.schedule_manage_v2.common.annotation.LoginSessionCheck;
import kyj.schedule_manage_v2.domain.user.dto.*;
import kyj.schedule_manage_v2.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kyj.schedule_manage_v2.common.util.Constants.LOGIN_SESSION_NAME;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //region user CRUD
    @PostMapping("/api/users")
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(request));
    }

    @GetMapping("/api/users/{user_id}")
    @LoginSessionCheck
    public ResponseEntity<SearchUserResponse> getUser(@PathVariable(name = "user_id") Long userId
            , @SessionAttribute(name = LOGIN_SESSION_NAME) LoginSessionData loginSessionData) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId,  loginSessionData));
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<SearchUserResponse>> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    @PutMapping("/api/users/{user_id}")
    @LoginSessionCheck
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable(name = "user_id") Long userId
            , @RequestBody UpdateUserRequest request
            , @SessionAttribute(name = LOGIN_SESSION_NAME) LoginSessionData loginSessionData) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, request, loginSessionData));
    }

    @DeleteMapping("/api/users/{user_id}")
    @LoginSessionCheck
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "user_id") Long userId
            , @SessionAttribute(name = LOGIN_SESSION_NAME) LoginSessionData loginSessionData
            , HttpSession session) {
        userService.deleteUser(userId, loginSessionData);
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    //endregion

    //region login
    @PostMapping("/api/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request
            , HttpSession session) {
        LoginResponse loginResponse = userService.login(request);
        LoginSessionData loginSessionData = LoginSessionData
                .builder()
                .id(loginResponse.id())
                .build();
        session.setAttribute(LOGIN_SESSION_NAME, loginSessionData);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @PostMapping("/api/logout")
    public ResponseEntity<Void> logOut(@SessionAttribute(name = LOGIN_SESSION_NAME) LoginSessionData loginSessionData
            , HttpSession session) {
        if(loginSessionData == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    //endregion
}
