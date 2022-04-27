package com.soma.lightening.post.controller;

import com.soma.lightening.post.domain.*;
import com.soma.lightening.post.dto.PostDto;
import com.soma.lightening.post.repository.PostRepository;
import com.soma.lightening.post.service.PostService;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

    @GetMapping("/api/posts")
    public Page<PostDto> posts(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "1024") Integer size,
                               @RequestParam(value="tag", required = false) String postTag, @RequestParam(value="type", required = false) String postType){
        Page<Post> posts;
        Page<PostDto> ret;
        PageRequest pageRequest = PageRequest.of(page, size);

        PostTag curTag = PostTag.MEAL;
        PostType curType = PostType.RECRUIT;

        try{ curTag = PostTag.valueOf(postTag); } catch(Exception e){ postTag = "NULL";}
        try{ curType = PostType.valueOf(postType);} catch(Exception e){ postType = "NULL";}

        if(postTag.equals("NULL") && postType.equals("NULL"))
            posts = postService.findPosts(pageRequest);
        else if(postTag.equals("NULL"))
            posts = postService.findPostsByType(curType, pageRequest);
        else if(postType.equals("NULL"))
            posts = postService.findPostsByTag(curTag, pageRequest);
        else
            posts = postService.findPostsByTagAndType(curTag, curType, pageRequest);
        
        ret = posts.map(p -> new PostDto(p));

        return ret;
    }
}
