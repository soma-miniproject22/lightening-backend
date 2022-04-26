package com.soma.lightening.post.repository;

import com.soma.lightening.post.domain.Post;
import com.soma.lightening.post.domain.PostTag;
import com.soma.lightening.post.domain.PostType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long>{

    public Optional<Post> findById(Long id);

    public List<Post> findAll();
    public List<Post> findAllByPostTag(PostTag postTag);
    public List<Post> findAllByPostType(PostType postType);
}
