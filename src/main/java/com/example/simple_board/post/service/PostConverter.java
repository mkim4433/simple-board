package com.example.simple_board.post.service;

import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostDto;
import org.springframework.stereotype.Service;

@Service
public class PostConverter {

    public PostDto toDto(PostEntity post) {

        return PostDto.builder()
                .id(post.getId())
                .boardId(post.getBoard().getId())
                .userName(post.getUserName())
                .password(post.getPassword())
                .email(post.getEmail())
                .status(post.getStatus())
                .title(post.getTitle())
                .content(post.getContent())
                .postedAt(post.getPostedAt())
                .build();
    }
}
