package com.soma.lightening.board.domain;

import com.soma.lightening.common.entity.OAuth2Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="participate")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participate {
    @Id @GeneratedValue
    @Column(name="participate_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id")
    private OAuth2Account account;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    @Enumerated
    private ParticipateCondition participateCondition; // NONE, CURIOUS, PARTICIPATE
}
