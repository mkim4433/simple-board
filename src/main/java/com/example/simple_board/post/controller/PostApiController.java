package com.example.simple_board.post.controller;

import com.example.simple_board.common.Api;
import com.example.simple_board.crud.CRUDAbstractApiController;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostDto;
import com.example.simple_board.post.model.PostRequest;
import com.example.simple_board.post.model.PostViewRequest;
import com.example.simple_board.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostApiController extends CRUDAbstractApiController<PostDto, PostEntity> {

    /*private final PostService postService;

    @PostMapping("")
    public PostDto create(@Valid @RequestBody PostRequest postRequest) {

        return postService.create(postRequest);
    }

    @PostMapping("/view")
    public PostDto view(@Valid @RequestBody PostViewRequest postViewRequest) {

        return postService.view(postViewRequest);
    }

    @GetMapping("/all")
    public Api<List<PostEntity>> getList(
            @PageableDefault(size = 10, page = 0, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return postService.all(pageable);
    }


    @PostMapping("/delete")
    public void delete(@Valid @RequestBody PostViewRequest postViewRequest) {

        postService.delete(postViewRequest);
    }*/
}
