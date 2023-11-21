package com.linkstock.repository;

import com.linkstock.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 카드 정보를 데이터베이스에서 조회 및 조작하는 데 사용되는 JpaRepository 인터페이스
 * Card 엔티티와 상호 작용하여 카드 정보를 관리한다.
 * @author : 박상희
 */
public interface CardRepository extends JpaRepository<Card, Long> {
    /**
     * 사용자 고유 번호와 카드 고유 번호를 기준으로 찾은 카드가 데이터베이스에 존재하는지 확인하는 메서드
     * @author : 박상희
     * @param userSeq : 사용자 고유 번호
     * @param cardSeq : 카드 고유 번호
     * @return - 카드가 존재할 경우 : true
     * @return - 카드가 존재하지 않을 경우 : false
     **/
    Boolean existsByUserUserSeqAndCardSeq(Long userSeq, Long cardSeq);

    /**
     * 사용자 고유 번호를 기준으로 카드 정보를 찾는 메서드
     * @author : 박상희
     * @param userSeq : 사용자 고유 번호
     * @return Optional<User> 객체로 래핑된 카드 정보 (카드 정보가 존재하지 않을 경우, 빈 Optional 반환)
     **/
    Optional<Card> findByUserUserSeq(Long userSeq);
}
