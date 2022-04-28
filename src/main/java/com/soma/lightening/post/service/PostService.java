package com.soma.lightening.post.service;

import com.soma.lightening.common.entity.OAuth2Account;
import com.soma.lightening.common.repository.OAuth2AccountRepository;
import com.soma.lightening.post.domain.Post;
import com.soma.lightening.post.domain.PostTag;
import com.soma.lightening.post.domain.PostType;
import com.soma.lightening.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final OAuth2AccountRepository accountRepository;

    // newPost는 여기에 작성하면 된다 (readOnly = false)
    // account의 정보는 accountRepository.findOne
    @Transactional
    public Long newPost(Long accountId, String appointmentDate, PostTag postTag, Date recruitEndDate, String postContent, int maxCount){
        OAuth2Account account = accountRepository.findById(accountId).get();

        Post post = Post.newPost(account, appointmentDate, postTag, recruitEndDate, postContent, maxCount);

        postRepository.save(post);
        return post.getId();
    }

    @Transactional(readOnly = true)
    public Page<Post> findPostsByTagAndType(String postTag, String postType, Pageable pageable){
        PostTag curTag;
        PostType curType;
        try{ curTag = PostTag.valueOf(postTag);} catch(Exception e){curTag = PostTag.ALL;}
        try{ curType = PostType.valueOf(postType);} catch(Exception e){curType = PostType.ALL;}

        if(curTag == PostTag.ALL && curType == PostType.ALL)
            return postRepository.findAll(pageable);
        else if(curTag == PostTag.ALL)
            return postRepository.findAllByPostType(curType, pageable);
        else if(curType == PostType.ALL)
            return postRepository.findAllByPostTag(curTag, pageable);
        return postRepository.findAllByPostTagAndPostType(curTag, curType,pageable);
    }
}
