package com.linkstock.service;

import com.linkstock.dto.request.SignUpRequestDTO;
import com.linkstock.dto.response.ResponseDTO;
import com.linkstock.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkstock.repository.UserRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    /**
     * 해당 이메일이 존재하는지 체크하는 메서드
     * @author : 박상희
     * @param email : 존재 여부를 검사할 이메일
     * @return - 해당 이메일이 존재하지 않을 경우 : 200
     * @return - 해당 이메일이 존재할 경우 : 500
     */
    @Override
    public ResponseEntity<?> checkEmail(final String email) {
        if (!userRepository.existsByEmail(email)) { // 해당 이메일이 존재하지 않을 경우
            ResponseDTO<Object> responseDTO = ResponseDTO.builder()
                    .message("이메일이 중복되지 않습니다.")
                    .build();

            return ResponseEntity.ok().body(responseDTO);
        }
        else {
            ResponseDTO<Object> responseDTO = ResponseDTO.builder()
                    .message("이메일이 중복됩니다.")
                    .build();

            return ResponseEntity
                    .internalServerError() // Error 500
                    .body(responseDTO);
        }
    }

    /**
     * 새로운 사용자를 생성하는 메서드
     * @author : 박상희
     * @param user : 회원 가입할 사용자 객체
     * @return 등록할 사용자 객체
     * @throws RuntimeException 사용자 데이터가 유효하지 않을 경우, 사용자 이메일이 이미 존재할 경우, 사용자 카드 내역 연동에 동의하지 않았을 경우 예외 발생
     */
    public User create(final User user) {
        if (user == null || user.getUserName() == null || user.getEmail() == null || user.getPassword() == null) { // 사용자 데이터가 유효하지 않을 경우
            throw new RuntimeException("회원 가입에 실패했습니다.");
        }

        final String email = user.getEmail();
        if (userRepository.existsByEmail(email)) { // 사용자 이메일이 이미 존재할 경우
            log.warn("이메일이 이미 존재합니다. {}", email);
            throw new RuntimeException("이메일이 이미 존재합니다.");
        }

        if (user.getAgree() == null || user.getAgree() == 0) { // 사용자 카드 내역 연동에 동의하지 않았을 경우
            log.warn("사용자 카드 내역 연동 동의가 필요합니다.");
            throw new RuntimeException("사용자 카드 내역 연동 동의가 필요합니다.");
        }

        return userRepository.save(user);
    }

    /**
     * 회원 가입 메서드
     * @author : 박상희
     * @param signUpRequestDTO : 회원 가입할 사용자 회원 가입 정보
     * @return - 회원 가입에 성공했을 경우 : 200
     * @return - 회원 가입에 실패했을 경우 : 500
     **/
    @Override
    public ResponseEntity<?> registerUser(final SignUpRequestDTO signUpRequestDTO) {
        try {
            // 요청을 이용해 저장할 사용자 만들기
            User user = User.builder()
                    .userName(signUpRequestDTO.getUserName()) // 사용자 이름
                    .email(signUpRequestDTO.getEmail()) // 사용자 이메일
                    .password(signUpRequestDTO.getPassword()) // 사용자 비밀번호
                    .agree(signUpRequestDTO.getAgree()) // 사용자 카드 내역 연동 동의 여부
                    .build();

            // 리포지터리에 사용자 저장
            create(user);

            ResponseDTO<Object> responseDTO = ResponseDTO.builder()
                    .message("회원 가입을 성공했습니다.")
                    .build();

            return ResponseEntity.ok().body(responseDTO);
        }
        catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder().message("회원 가입을 실패했습니다. " + e.getMessage()).build();

            return ResponseEntity
                    .internalServerError() // Error 500
                    .body(responseDTO);
        }
    }
}