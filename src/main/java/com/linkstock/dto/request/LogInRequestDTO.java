package com.linkstock.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자 로그인 요청 DTO
 * @author : 박상희
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogInRequestDTO {
    private String email; // 사용자 이메일
    private String password; // 사용자 비밀번호
}
