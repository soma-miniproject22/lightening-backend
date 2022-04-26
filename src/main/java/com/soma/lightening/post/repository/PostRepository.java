package com.soma.lightening.post.repository;

import com.soma.lightening.post.domain.Post;
import com.soma.lightening.post.domain.PostTag;
import com.soma.lightening.post.domain.PostType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);
    List<Post> findAllByPostTag(PostTag postTag);
    List<Post> findAllByPostType(PostType postType);
}
