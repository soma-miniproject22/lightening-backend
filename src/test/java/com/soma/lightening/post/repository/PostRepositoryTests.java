package com.soma.lightening.post.repository;

import com.soma.lightening.common.entity.OAuth2Account;
import com.soma.lightening.common.repository.OAuth2AccountRepository;
import com.soma.lightening.post.domain.*;
import com.soma.lightening.post.service.PostService;
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

        List<Post> all = postRepository.findAll();

        for(var cur : all)
            System.out.println(cur.getPostContent());
    }
}
