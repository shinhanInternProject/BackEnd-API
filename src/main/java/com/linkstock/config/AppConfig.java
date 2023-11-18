package com.linkstock.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class AppConfig {
    @PersistenceContext // EntityManager는 각 서비스마다 새롭게 생성해서 주입
    private EntityManager em;

    @Bean // 생성
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(em);
    }
}
