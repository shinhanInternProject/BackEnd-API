package com.linkstock.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자 회원 가입 요청 DTO
 * @author : 박상희
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDTO {
    private String userName; // 사용자 이름
    private String email; // 사용자 이메일
    private String password; // 사용자 비밀번호
    private Integer agree; // 사용자 카드 내역 연동 동의 여부
}
