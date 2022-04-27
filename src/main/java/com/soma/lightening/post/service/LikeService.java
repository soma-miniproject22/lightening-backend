package com.soma.lightening.post.service;

import com.soma.lightening.common.entity.OAuth2Account;
import com.soma.lightening.common.repository.OAuth2AccountRepository;
import com.soma.lightening.post.domain.Like;
import com.soma.lightening.post.domain.LikeType;
import com.soma.lightening.post.domain.Post;
import com.soma.lightening.post.repository.LikeRepository;
import com.soma.lightening.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final OAuth2AccountRepository accountRepository;

    @Transactional
    public Long newLike(Long accountId, Long postId, LikeType likeType){
        OAuth2Account account = accountRepository.findById(accountId).get();
        Post post = postRepository.findById(postId).get();

        // 이후 예외처리 사용
        if(account.getId() == post.getAccount().getId()){
            throw new IllegalStateException("자신의 게시글에 Like할 수 없습니다.");
        }

        Like like = Like.newLike(account, post, likeType);
        likeRepository.save(like);
        return like.getId();
    }

    public List<Like> findLikesByAccountId(Long accountId){
        return likeRepository.findAllByAccount_Id(accountId);
    }
}
