package com.soma.lightening.board.service;

import com.soma.lightening.board.domain.Post;
import com.soma.lightening.board.repository.AccountRepository;
import com.soma.lightening.board.repository.PostRepository;
import com.soma.lightening.common.entity.OAuth2Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;

    // newPost는 여기에 작성하면 된다 (readOnly = false)
    // account의 정보는 accountRepository.findOne
    public Long newPost(Long accountId, String recruitType, String body, int maxParticipate, LocalDateTime appointmentTime, LocalDateTime endTime){
        OAuth2Account account = accountRepository.findOne(accountId);
        Post post = Post.newPost(account, recruitType, body, maxParticipate, appointmentTime, endTime);

        postRepository.save(post);
        return post.getId();
    }

    public List<Post> findPosts(){
        return postRepository.findAll();
    }

    public List<Post> findPostsByTag(String recruitType){
        return postRepository.findAllByTag(recruitType);
    }
}
