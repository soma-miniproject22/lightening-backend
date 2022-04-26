package com.soma.lightening.post.controller;

import com.soma.lightening.post.domain.*;
import com.soma.lightening.post.repository.PostRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private final PostRepository postRepository;

    @GetMapping("/api/posts")
    public List<PostDto> posts(){
        List<Post> posts = postRepository.findAll();
        List<PostDto> ret = posts.stream().map(p -> new PostDto(p)).collect(Collectors.toList());

        return ret;
    }

    @GetMapping("/api/posts/tag/{postTag}")
    public List<PostDto> postsByTag(@PathVariable("postTag") String postTag){
        PostTag curTag = PostTag.valueOf(postTag);
        List<Post> posts = postRepository.findAllByPostTag(curTag);
        List<PostDto> ret = posts.stream().map(p -> new PostDto(p)).collect(Collectors.toList());

        return ret;
    }

    @GetMapping("/api/posts/type/{postType}")
    public List<PostDto> postsByRecruit(@PathVariable("postType") String postType){
        PostType curType = PostType.valueOf(postType);
        List<Post> posts = postRepository.findAllByPostType(curType);
        List<PostDto> ret = posts.stream().map(p -> new PostDto(p)).collect(Collectors.toList());

        return ret;
    }

    @Data
    @Getter
    static class PostDto{
        private Long postId; // 포스트 id
        private Long accountId; // 작정자 id
        private PostTag postTag; // post 태그
        private PostType postType; // post 모집완료상태
        //private List<LikeDto> likes; // 관심/참여 인원
        private String postContent; // 내용
        private Date meetDate; // 약속 시간
        private Date recruitEndDate; // 게시 종료 시간
        private int maxCount; // 최대 인원

        public PostDto(Post post){
            this.postId = post.getId();
            this.accountId = post.getAccount().getId();
            this.postType = post.getPostType();
            //this.likes = post.getLikeList().stream().map(l -> new LikeDto(l)).collect(Collectors.toList());
            this.postTag = post.getPostTag();
            this.postContent = post.getPostContent();
            this.meetDate = post.getDate();
            this.recruitEndDate = post.getRecruitEndDate();
            this.maxCount = post.getMaxCount();
        }
    }

    @Data
    @Getter
    static class LikeDto{
        private Long accountId; // 참여자
        private LikeType likeType; // 참여 타입(관심, 참가)

        public LikeDto(Like like) {
            this.accountId = like.getAccount().getId();
            this.likeType = like.getLikeType();
        }
    }
}
