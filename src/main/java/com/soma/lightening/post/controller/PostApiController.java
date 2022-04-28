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
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

    @GetMapping("/api/posts")
    public Page<PostDto> posts(Pageable pageable,
                               @RequestParam(value="tag", defaultValue = "ALL") String postTag,
                               @RequestParam(value="type", defaultValue = "ALL") String postType){
        return postService.findPostsByTagAndType(postTag, postType, pageable).map(p -> new PostDto(p));
    }
}
