package com.soma.lightening.post.dto;

import com.soma.lightening.post.domain.Post;
import com.soma.lightening.post.domain.PostTag;
import com.soma.lightening.post.domain.PostType;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
public class PostDto{
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
