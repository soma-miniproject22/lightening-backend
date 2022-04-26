package com.soma.lightening.post.service;

import com.soma.lightening.common.entity.OAuth2Account;
import com.soma.lightening.common.repository.OAuth2AccountRepository;
import com.soma.lightening.post.domain.Post;
import com.soma.lightening.post.domain.PostTag;
import com.soma.lightening.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final OAuth2AccountRepository accountRepository;

    // newPost는 여기에 작성하면 된다 (readOnly = false)
    // account의 정보는 accountRepository.findOne
    public Long newPost(Long accountId, String appointmentDate, PostTag postTag, Date recruitEndDate, String postContent, int maxCount){
        OAuth2Account account = accountRepository.findById(accountId).get();

        Post post = Post.newPost(account, appointmentDate, postTag, recruitEndDate, postContent, maxCount);

        postRepository.save(post);
        return post.getId();
    }

    public List<Post> findPosts(){
        return postRepository.findAll();
    }

    public List<Post> findPostsByTag(PostTag postTag){
        return postRepository.findAllByPostTag(postTag);
    }
}
