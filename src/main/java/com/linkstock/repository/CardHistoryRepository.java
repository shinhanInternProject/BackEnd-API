package com.linkstock.repository;

import com.linkstock.entity.CardHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 카드 내역 정보를 데이터베이스에서 조회 및 조작하는 데 사용되는 JpaRepository 인터페이스
 * CardHistory 엔티티와 상호 작용하여 카드 내역 정보를 관리한다.
 * @author : 박상희
 */
public interface CardHistoryRepository extends JpaRepository<CardHistory, Long> {
    /**
     * 카드 고유 번호와 해당 월의 모든 카드 내역을 조회하는 메서드
     * @param cardSeq : 카드 고유 번호
     * @param month : 월
     * @return CardHistory 리스트
     */
    @Query(value = "select ch from CardHistory ch where ch.card.cardSeq = ?1 and function('month', ch.paymentDate) = ?2")
    List<CardHistory> findAllByCardCardSeqAAndMonth(Long cardSeq, int month);
}
