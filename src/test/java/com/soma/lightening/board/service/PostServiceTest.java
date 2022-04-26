package com.soma.lightening.board.service;

import com.soma.lightening.board.domain.Post;
import com.soma.lightening.board.repository.PostRepository;
import com.soma.lightening.common.entity.OAuth2Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PostServiceTest {
    @Autowired
    private EntityManager em;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AccountService accountService;

    @Test
    public void Post작성() {
        OAuth2Account account = new OAuth2Account();
        //accountService.join(account);

        System.out.println(account.getId());

        Long pId = postService.newPost(account.getId(), "all", "body", 5, LocalDateTime.now(), LocalDateTime.MAX);
        Post post = postRepository.findOne(pId);

        Assertions.assertThat(post.getAccount().getId()).isEqualTo(account.getId());
    }

    @Test
    public void 모든Post조회() {
        OAuth2Account account = new OAuth2Account();
        OAuth2Account account2 = new OAuth2Account();
//        accountService.join(account);
//        accountService.join(account2);

        System.out.println(account.getId());
        System.out.println(account2.getId());

        for (int i = 0; i < 5; i++) {
            postService.newPost(account.getId(), "all", "body" + i, 5, LocalDateTime.now(), LocalDateTime.MAX);
        }

        for (int i = 0; i < 5; i++) {
            postService.newPost(account2.getId(), "all", "body2" + i, 5, LocalDateTime.now(), LocalDateTime.MAX);
        }

        List<Post> posts = postService.findPosts();

        for (var p : posts) {
            System.out.print(p.getAccount().getId() + " ");
            System.out.println(p.getBody());
        }
    }
}