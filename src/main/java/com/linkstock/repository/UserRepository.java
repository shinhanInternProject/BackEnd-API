package com.linkstock.repository;

import com.linkstock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 사용자 정보를 데이터베이스에서 조회 및 조작하는 데 사용되는 JpaRepository 인터페이스
 * User 엔티티와 상호 작용하여 사용자 정보를 관리한다.
 * @author : 박상희
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 해당 이메일이 데이터베이스에 존재하는지 확인하는 메서드
     * @author : 박상희
     * @param email : 확인할 이메일
     * @return - 이메일이 존재할 경우 : true
     * @return - 이메일이 존재하지 않을 경우 : false
     **/
    Boolean existsByEmail(String email);

    /**
     * 해당 이메일을 기준으로 사용자 정보를 찾는 메서드
     * @author : 박상희
     * @param email : 찾을 사용자 이메일
     * @return Optional<User> 객체로 래핑된 사용자 정보 (사용자 정보가 존재하지 않을 경우, 빈 Optional 반환)
     **/
    Optional<User> findByEmail(String email);

    /**
     * 이메일과 비밀번호로 사용자 정보를 찾는 메서드
     * @author : 박상희
     * @param email : 이메일
     * @param password : 비밀번호
     * @return - 사용자 정보가 존재할 경우 : 사용자 객체
     * @return - 사용자 정보가 존재하지 않을 경우 : null
     **/
    User findByEmailAndPassword(String email, String password);
}
