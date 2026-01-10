package kyj.schedule_manage_v2.domain.user.service;

import kyj.schedule_manage_v2.common.config.PasswordEncoder;
import kyj.schedule_manage_v2.common.exception.DuplicatedErrorException;
import kyj.schedule_manage_v2.common.exception.LoginErrorException;
import kyj.schedule_manage_v2.common.exception.NotFoundDataErrorException;
import kyj.schedule_manage_v2.common.exception.UnAuthorizedAccessErrorException;
import kyj.schedule_manage_v2.domain.user.dto.*;
import kyj.schedule_manage_v2.domain.user.entity.User;
import kyj.schedule_manage_v2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //region user CRUD
    @Transactional
    public CreateUserResponse saveUser(CreateUserRequest request) {
        User user = new User(request.getUserName(), request.getEmail(), passwordEncoder.encode(request.getPassword()));

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicatedErrorException("이미 가입된 이메일입니다, 로그인 후 이용하세요");
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

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public SearchUserResponse getUser(Long userId, LoginSessionData loginSessionData) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundDataErrorException("없는 유저 입니다"));

        if (!user.getId().equals(loginSessionData.id())) {
            throw new UnAuthorizedAccessErrorException("본인의 계정 정보만 조회 할 수 있습니다");
        }

        return SearchUserResponse
                .builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
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

    @Transactional
    public UpdateUserResponse updateUser(Long userId, UpdateUserRequest request, LoginSessionData loginSessionData) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundDataErrorException("없는 유저 입니다"));

        if (!user.getId().equals(loginSessionData.id())) {
            throw new UnAuthorizedAccessErrorException("본인의 계정 정보만 수정 할 수 있습니다");
        }

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicatedErrorException("이미 가입된 이메일입니다, 다른 이메일로 수정 해주세요");
        }

        user.update(request.getEmail()
                , request.getUserName()
                , request.getPassword() != null ? passwordEncoder.encode(request.getPassword()) : null);

        return UpdateUserResponse
                .builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();
    }

    @Transactional
    public void deleteUser(Long userId, LoginSessionData loginSessionData) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundDataErrorException("없는 유저 입니다"));

        if (!user.getId().equals(loginSessionData.id())) {
            throw new UnAuthorizedAccessErrorException("본인의 계정 정보만 삭제 할 수 있습니다");
        }

        userRepository.deleteById(userId);
    }
    //endregion

    //region login
    public LoginResponse login(LoginRequest request) {
        User loginUser = userRepository.findUserByEmail(request.email())
                .orElseThrow(() -> new LoginErrorException("아이디와 비밀번호를 다시 확인해주세요"));

        if (!passwordEncoder.matches(request.password(), loginUser.getPassword())) {
            throw new LoginErrorException("아이디와 비밀번호를 다시 확인해주세요");
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
