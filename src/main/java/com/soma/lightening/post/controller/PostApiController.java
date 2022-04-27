package com.soma.lightening.post.controller;

import com.soma.lightening.post.domain.*;
import com.soma.lightening.post.repository.PostRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<PostDto> posts(@RequestParam(value="tag", defaultValue="NULL") String postTag, @RequestParam(value="type", defaultValue="NULL") String postType){
        List<Post> posts;
        List<PostDto> ret;

        if(postTag.equals("NULL") && postType.equals("NULL")) {
            posts = postRepository.findAll();
            ret = posts.stream().map(p -> new PostDto(p)).collect(Collectors.toList());
        }
        else if(postTag.equals("NULL")){
            PostType curType = PostType.valueOf(postType);
            posts = postRepository.findAllByPostType(curType);
            ret = posts.stream().map(p -> new PostDto(p)).collect(Collectors.toList());
        }
        else if(postType.equals("NULL")){
            PostTag curTag = PostTag.valueOf(postTag);
            posts = postRepository.findAllByPostTag(curTag);
            ret = posts.stream().map(p -> new PostDto(p)).collect(Collectors.toList());
        }
        else{
            PostTag curTag = PostTag.valueOf(postTag);
            PostType curType = PostType.valueOf(postType);
            posts = postRepository.findAllByPostTagAndPostType(curTag, curType);
            ret = posts.stream().map(p -> new PostDto(p)).collect(Collectors.toList());
        }

        return ret;
    }

    @Data
    @Getter
    static class PostDto{
        private Long postId; // 포스트 id
        private Long accountId; // 작정자 id
        private String accountUsername;
        private String accountNickname;
        private String accountImage;
        private PostTag postTag; // post 태그
        private PostType postType; // post 모집완료상태
        private List<EmotionDto> emotions; // 관심/참여 인원
        private String postContent; // 내용
        private Date meetDate; // 약속 시간
        private Date recruitEndDate; // 게시 종료 시간
        private int maxCount; // 최대 인원

        public PostDto(Post post){
            this.postId = post.getId();
            this.accountId = post.getAccount().getId();
            this.accountNickname = post.getAccount().getNickname();
            this.accountUsername = post.getAccount().getUsername();
            this.accountImage = post.getAccount().getProfileImage();
            this.postType = post.getPostType();
            this.emotions = post.getEmotionList().stream().map(e -> new EmotionDto(e)).collect(Collectors.toList());
            this.postTag = post.getPostTag();
            this.postContent = post.getPostContent();
            this.meetDate = post.getDate();
            this.recruitEndDate = post.getRecruitEndDate();
            this.maxCount = post.getMaxCount();
        }
    }

    @Data
    @Getter
    static class EmotionDto{
        private Long accountId; // 참여자
        private String accountUsername; // 참여자 아이디
        private EmotionType emotionType; // 참여 타입(관심, 참가)

        public EmotionDto(Emotion emotion) {
            this.accountId = emotion.getAccount().getId();
            this.accountUsername = emotion.getAccount().getUsername();
            this.emotionType = emotion.getEmotionType();
        }
    }
}
