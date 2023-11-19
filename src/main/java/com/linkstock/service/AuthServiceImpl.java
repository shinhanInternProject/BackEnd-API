package com.linkstock.service;

import com.linkstock.dto.response.ResponseDTO;
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
     * @return - 사용자가 입력한 사용자 이메일이 존재하지 않을 경우 : 200
     * @return - 사용자가 입력한 사용자 이메일이 존재할 경우 : 500
     */
    @Override
    public ResponseEntity<?> checkEmail(String email) {
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
}
