package com.soma.lightening.post.repository;

import com.soma.lightening.post.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringBootTest
@ActiveProfiles("prod") // 원래안댐
public class PostRepositoryTests {
    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    void postSaveTest() {
        Post post = Post.newPost(null, "오늘 오후까지", new Date(),"aa",1024);
        post = postRepository.saveAndFlush(post);
        System.out.println(post.getDate());
    }
}
