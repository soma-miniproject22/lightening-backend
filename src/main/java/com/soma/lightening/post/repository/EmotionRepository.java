package com.soma.lightening.post.repository;

import com.soma.lightening.post.domain.Emotion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
    @EntityGraph(attributePaths = "account")
    public List<Emotion> findAllByAccount_Id(Long accountId);
}
