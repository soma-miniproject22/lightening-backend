package com.soma.lightening.post.dto;

import com.soma.lightening.post.domain.Emotion;
import com.soma.lightening.post.domain.EmotionType;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class EmotionDto{
    private Long accountId; // 참여자
    private String accountUsername; // 참여자 아이디
    private EmotionType emotionType; // 참여 타입(관심, 참가)

    public EmotionDto(Emotion emotion) {
        this.accountId = emotion.getAccount().getId();
        this.accountUsername = emotion.getAccount().getUsername();
        this.emotionType = emotion.getEmotionType();
    }
}