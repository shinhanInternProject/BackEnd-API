package com.linkstock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 카드 내역 정보를 나타내는 엔티티 클래스
 * @author : 박상희
 **/
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card_history")
public class CardHistory {
    @Id
    @Column(name = "card_history_seq", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardHistorySeq; // 카드 내역 고유 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_seq")
    private Card card; // 해당 카드 내역의 카드

    @Column(name = "payment_category", nullable = false)
    private String paymentCategory; // 소비 업종

    @Column(name = "payment_detail", nullable = false)
    private String paymentDetail; // 소비 상세 내역

    @Column(name = "payment_price", nullable = false)
    private int paymentPrice; // 소비 금액

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate; // 소비 날짜
}
