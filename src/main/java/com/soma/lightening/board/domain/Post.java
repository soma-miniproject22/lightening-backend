package com.soma.lightening.board.domain;

import com.soma.lightening.entity.OAuth2Account;
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
    @OneToMany(mappedBy="post", cascade = CascadeType.ALL)
    private List<Participate> participates = new ArrayList<>();

    @Enumerated
    private RECRUIT_CONDITION recruitCondition; // RECRUIT, NON_RECRUIT

    private String body;

    private LocalDateTime appointmentTime;
    private LocalDateTime endTime;

    private int maxParticipate;
}
