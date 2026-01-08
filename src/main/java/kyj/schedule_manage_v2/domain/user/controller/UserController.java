package kyj.schedule_manage_v2.domain.user.controller;

import jakarta.servlet.http.HttpSession;
import kyj.schedule_manage_v2.domain.user.dto.*;
import kyj.schedule_manage_v2.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //region user CRUD
    @PostMapping("/api/users")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(request));
    }

    @GetMapping("/api/users/{user_id}")
    public ResponseEntity<SearchUserResponse> getUser(@PathVariable(name = "user_id") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId));
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<SearchUserResponse>> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    @PutMapping("/api/users/{user_id}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable(name = "user_id") Long userId, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, request));
    }

    @DeleteMapping("/api/users/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "user_id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    //endregion

    //region login
    @PostMapping("/api/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpSession session) {
        LoginResponse loginResponse = userService.login(request);
        UserSession userSession = UserSession
                .builder()
                .id(loginResponse.id())
                .email(loginResponse.email())
                .userName(loginResponse.userName())
                .build();
        session.setAttribute("loginUser", userSession);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @PostMapping("/api/logOut")
    public ResponseEntity<Void> logOut(@SessionAttribute(name = "loginUser") HttpSession session) {
        if(session == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    //endregion
}
