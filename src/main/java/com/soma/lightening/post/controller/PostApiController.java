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
import org.springframework.data.domain.Pageable;
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
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

    @GetMapping("/api/posts")
    public Page<PostDto> posts(Pageable pageable,
                               @RequestParam(value="tag", required = false) String postTag,
                               @RequestParam(value="type", required = false) String postType){
        PostTag curTag;
        PostType curType;
        try{ curTag = PostTag.valueOf(postTag);} catch(Exception e){curTag = null;}
        try{ curType = PostType.valueOf(postType);} catch(Exception e){curType = null;}

        boolean tagNull = Objects.equals(curTag, null);
        boolean typeNull = Objects.equals(curType, null);

        if(tagNull && typeNull)
            return postService.findPosts(pageable).map(p -> new PostDto(p));
        else if(tagNull)
            return postService.findPostsByType(curType, pageable).map(p -> new PostDto(p));
        else if(typeNull)
            return postService.findPostsByTag(curTag, pageable).map(p -> new PostDto(p));
        return postService.findPostsByTagAndType(curTag, curType, pageable).map(p -> new PostDto(p));
    }
}
