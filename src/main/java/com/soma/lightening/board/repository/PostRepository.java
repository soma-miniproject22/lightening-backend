package com.soma.lightening.board.repository;

import com.soma.lightening.board.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;

    public void save(Post post){
        em.persist(post);
    }

    public Post findOne(Long id){
        return em.find(Post.class, id);
    }

    public List<Post> findAll(){
        return em.createQuery("select p from Post p", Post.class).getResultList();
    }

    public List<Post> findAllByTag(String recruitType){
        // not checked
        return em.createQuery(
                "select p from Post p" +
                        " where p.recruitType = :recruitType",Post.class).getResultList();
    }
}
