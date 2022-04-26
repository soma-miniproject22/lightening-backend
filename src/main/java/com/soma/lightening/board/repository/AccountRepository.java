package com.soma.lightening.board.repository;

import com.soma.lightening.entity.OAuth2Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AccountRepository {
    private final EntityManager em;

//    public void save(OAuth2Account account){
//        em.persist(account);
//    }

    public OAuth2Account findOne(Long id){
        return em.find(OAuth2Account.class, id);
    }
}
