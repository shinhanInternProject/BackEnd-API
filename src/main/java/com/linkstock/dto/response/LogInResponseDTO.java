package com.linkstock.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자 로그인 응답 DTO
 * @author : 박상희
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogInResponseDTO {
    private String userName; // 사용자 이름
    private String accessToken; // Access Token
}
