package com.linkstock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 카드 정보를 나타내는 엔티티 클래스
 * @author : 박상희
 **/
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
public class Card {
    @Id
    @Column(name = "card_seq", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardSeq; // 카드 고유 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user; // 카드 사용자

    @Column(name = "card_type", nullable = false)
    private Integer cardType; // 카드 종류  // 0 : 신용카드, 1 : 체크카드

    @Column(name = "card_name", nullable = false)
    private String cardName;

    @PrePersist // 엔티티 객체가 영구 저장소에 저장되기 전에 실행해야 하는 메서드를 지정하는 어노테이션
    public void prePersist() {
        this.cardType = this.cardType == null ? 0 : this.cardType;
    }
}
