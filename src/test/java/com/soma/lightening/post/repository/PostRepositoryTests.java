package com.soma.lightening.post.repository;

import com.soma.lightening.common.entity.OAuth2Account;
import com.soma.lightening.common.repository.OAuth2AccountRepository;
import com.soma.lightening.post.domain.*;
import com.soma.lightening.post.service.EmotionService;
import com.soma.lightening.post.service.PostService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ActiveProfiles("local")
public class PostRepositoryTests {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private EmotionRepository emotionRepository;
    @Autowired
    private EmotionService emotionService;
    @Autowired
    private OAuth2AccountRepository oAuth2AccountRepository;

    @Test
    @Transactional
    void postSaveTest() {
        Post post = Post.newPost(null, "오늘 오후까지", PostTag.MEAL, new Date(),"aa",1024);
        post = postRepository.save(post);
        System.out.println(post.getDate());
    }

    @Test
    @Transactional
    @Rollback(false)
    void postsPrint(){
        OAuth2Account account = new OAuth2Account("asdasdsafasf","이상빈");
        oAuth2AccountRepository.save(account);

        OAuth2Account account2 = new OAuth2Account("wasdsadasdasdasd","김수홍");
        oAuth2AccountRepository.save(account2);

        Long id = postService.newPost(account.getId(),"오늘 오후까지", PostTag.MEAL, new Date(), "tempA",1024);
        Post post = postRepository.findById(id).get();
        postService.newPost(account.getId(),"오늘 오후까지", PostTag.COFFEE, new Date(), "tempB",1024);
        Long cid = postService.newPost(account2.getId(), "오늘 오후까지", PostTag.ALCOHOL, new Date(), "tempC", 1024);
        postService.newPost(account2.getId(),"오늘 오후까지", PostTag.MEAL, new Date(), "tempD",1024);

        Post p = postRepository.findById(cid).get();
        p.setPostType(PostType.COMPLETED);
    }

    @Test
    @Transactional
    void EmotionTest(){
//        OAuth2Account account = new OAuth2Account("asdasdsafasf","이상빈");
//        oAuth2AccountRepository.save(account);
//
//        OAuth2Account account2 = new OAuth2Account("wasdsadasdasdasd","김수홍");
//        oAuth2AccountRepository.save(account2);
//
//        Long id = postService.newPost(account.getId(),"오늘 오후까지", PostTag.MEAL, new Date(), "tempA",1024);
//        Long cid = postService.newPost(account.getId(), "오늘 오후까지", PostTag.ALCOHOL, new Date(), "tempC", 1024);
//
//        System.out.println(id);
//        System.out.println(cid);
//
//        Long lid = emotionService.newLike(account2.getId(), id, LikeType.WILLING);
//        Long lid2 = emotionService.newLike(account2.getId(), cid, LikeType.PARTICIPATE);
//
//        Like like = emotionRepository.findById(lid).get();
//        Like like2 = emotionRepository.findById(lid2).get();
//
//        Assertions.assertThat(id).isEqualTo(like.getPost().getId());
//        Assertions.assertThat(cid).isEqualTo(like2.getPost().getId());
//
//        System.out.println(like.getPost().getPostTag());
//        System.out.println(like2.getPost().getPostTag());
    }
}
