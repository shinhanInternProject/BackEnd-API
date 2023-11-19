package com.linkstock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 사용자 정보를 나타내는 엔티티 클래스
 * @author : 박상희
 **/
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @Column(name = "user_seq", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PRIMARY KEY AUTO_INCREMENT
    private Long userSeq; // 사용자 고유 번호

    @Column(name = "user_name", nullable = false)
    private String userName; // 사용자 이름

    @Column(name = "email", nullable = false)
    private String email; // 사용자 이메일

    @Column(name = "password", nullable = false)
    private String password; // 사용자 비밀번호

    @Column(name = "agree", nullable = false)
    private Integer agree; // 사용자 카드 내역 연동 동의 여부

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt; // 사용자 계정 생성 날짜

    @PrePersist // 엔티티 객체가 영구 저장소에 저장되기 전에 실행해야 하는 메서드를 지정하는 어노테이션
    public void prePersist(){
        this.agree = this.agree == null ? 0 : this.agree;
    }
}
