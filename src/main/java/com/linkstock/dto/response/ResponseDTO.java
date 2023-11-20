package com.linkstock.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 공통 응답 DTO
 * @author : 박상희
 * @param <T> : 데이터의 타입을 나타내는 제네릭 타입
 **/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T> {
    private String message;
    private T data;
}
