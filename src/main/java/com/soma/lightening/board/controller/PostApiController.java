package com.soma.lightening.board.controller;

import com.soma.lightening.board.domain.ParticipateCondition;
import com.soma.lightening.board.domain.Participate;
import com.soma.lightening.board.domain.Post;
import com.soma.lightening.board.domain.RecruitCondition;
import com.soma.lightening.board.repository.PostRepository;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private final PostRepository postRepository;

    @GetMapping("/board")
    public List<PostDto> posts(){
        List<Post> posts = postRepository.findAll();
        List<PostDto> ret = posts.stream().map(p -> new PostDto(p)).collect(Collectors.toList());

        return ret;
    }

    @Data
    @Getter @Setter
    static class PostDto{
        private Long id;
        private Long accountId;
        private RecruitCondition recruitCondition;
        private List<ParticipateDto> participates;
        private String recruitType;
        private String body;
        private LocalDateTime appointmentTime;
        private LocalDateTime endTime;
        private int maxParticipate;

        public PostDto(Post post){
            this.id = post.getId();
            this.accountId = post.getAccount().getId();
            this.recruitCondition = post.getRecruitCondition();
            this.participates = post.getParticipates().stream().map(p -> new ParticipateDto(p)).collect(Collectors.toList());
            this.recruitType = post.getRecruitType();
            this.body = post.getBody();
            this.appointmentTime = post.getAppointmentTime();
            this.endTime = post.getEndTime();
            this.maxParticipate = post.getMaxParticipate();
        }
    }

    @Data
    @Getter @Setter
    static class ParticipateDto{
        private Long accountId;
        private ParticipateCondition participateCondition;

        public ParticipateDto(Participate participate) {
            this.accountId = participate.getAccount().getId();
            this.participateCondition = participate.getParticipateCondition();
        }
    }
}
