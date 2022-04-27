package com.soma.lightening.post.repository;

import com.soma.lightening.common.entity.OAuth2Account;
import com.soma.lightening.post.domain.Like;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @EntityGraph(attributePaths = "account") // 엔티티그래프 통해 EAGER로 가져온다.
    public Optional<Like> findById(Long id);

    @EntityGraph(attributePaths = "account")
    public List<Like> findAllByAccount_Id(Long accountId);
}
