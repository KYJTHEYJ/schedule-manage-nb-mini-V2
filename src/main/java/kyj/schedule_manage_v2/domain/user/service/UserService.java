package kyj.schedule_manage_v2.domain.user.service;

import kyj.schedule_manage_v2.domain.user.dto.*;
import kyj.schedule_manage_v2.domain.user.entity.User;
import kyj.schedule_manage_v2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //region user CRUD
    public CreateUserResponse saveUser(CreateUserRequest request) {
        User user = new User(request.getUserName(), request.getEmail(), request.getPassword());

        if(request.getPassword() == null || request.getPassword().length() < 8) {
            throw new IllegalStateException("비밀번호는 8자리 이상 입력되어야 합니다");
        }

        User saveUser = userRepository.save(user);
        return CreateUserResponse
                .builder()
                .id(saveUser.getId())
                .userName(saveUser.getUserName())
                .email(saveUser.getEmail())
                .createAt(saveUser.getCreateAt())
                .updateAt(saveUser.getUpdateAt())
                .build();
    }

    public SearchUserResponse getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("없는 유저 입니다"));

        return SearchUserResponse
                .builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();
    }

    public List<SearchUserResponse> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(user -> SearchUserResponse
                        .builder()
                        .id(user.getId())
                        .userName(user.getUserName())
                        .email(user.getEmail())
                        .createAt(user.getCreateAt())
                        .updateAt(user.getUpdateAt())
                        .build())
                .toList();
    }

    public UpdateUserResponse updateUser(Long userId, UpdateUserRequest request, LoginSessionData loginSessionData) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("없는 유저 입니다"));

        if(!user.getId().equals(loginSessionData.id())) {
            throw new IllegalStateException("본인의 계정 정보만 수정 할 수 있습니다");
        }

        user.update(request.getUserName(), request.getEmail(),  request.getPassword());

        return UpdateUserResponse
                .builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();
    }

    public void deleteUser(Long userId, LoginSessionData loginSessionData) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("없는 유저 입니다"));

        if(!user.getId().equals(loginSessionData.id())) {
            throw new IllegalStateException("본인의 계정 정보만 수정 할 수 있습니다");
        }

        userRepository.deleteById(userId);
    }
    //endregion

    //region login
    public LoginResponse login(LoginRequest request) {
       User loginUser = userRepository.findUserByEmail(request.email())
               .orElseThrow(() -> new IllegalStateException("아이디와 비밀번호를 다시 확인해주세요"));

       if(!loginUser.getPassword().equals(request.password())) {
           throw new IllegalStateException("아이디와 비밀번호를 다시 확인해주세요");
       }

       return LoginResponse
               .builder()
               .id(loginUser.getId())
               .email(loginUser.getEmail())
               .userName(loginUser.getUserName())
               .build();
    }
    //endregion
}
