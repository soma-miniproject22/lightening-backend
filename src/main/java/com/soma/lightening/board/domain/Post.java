package com.soma.lightening.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soma.lightening.common.entity.OAuth2Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Post {
    @Id @GeneratedValue
    @Column(name="post_id")
    private Long id;

    // 주인 엔티티 - Post
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="account_id")
    private OAuth2Account account;

    // 주인 엔티티 - Participated
    @JsonIgnore
    @OneToMany(mappedBy="post", cascade = CascadeType.ALL)
    private List<Participate> participates = new ArrayList<>();

    @Enumerated
    private RecruitCondition recruitCondition; // RECRUIT, NON_RECRUIT

    // String이면 split으로 태그 사용?
    private String recruitType;
    private String body;

    private LocalDateTime appointmentTime;
    private LocalDateTime endTime;

    private int maxParticipate;

    public static Post newPost(OAuth2Account account, String recruitType, String body, int maxParticipate, LocalDateTime appointmentTime, LocalDateTime endTime){
        Post post = new Post();
        post.setAccount(account);
        post.setRecruitType(recruitType);
        post.setBody(body);
        post.setMaxParticipate(maxParticipate);
        post.setAppointmentTime(appointmentTime);
        post.setEndTime(endTime);

        // default
        post.recruitCondition = RecruitCondition.RECRUIT;

        return post;
    }
}
