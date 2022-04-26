package com.soma.lightening.board;

import com.soma.lightening.board.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        System.out.println("init db progress");
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
//            OAuth2Account account1 =
//
//            Post post1 = createPost();
//            em.persist(post);
        }

//        private OAuth2Account createAccount(String name) {
//            OAuth2Account account = new OAuth2Account(name);
//            return account;
//        }
//
//        private Post createPost() {
//            Post post = new Post();
//
//            return post;
//        }
    }
}
