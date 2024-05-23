package com.study.communityService.controller;

import com.study.communityService.controller.port.PostService;
import com.study.communityService.controller.response.PostResponse;
import com.study.communityService.domain.PostCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody PostCreate postCreate) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PostResponse.from(postService.create(postCreate)));
    }
}
