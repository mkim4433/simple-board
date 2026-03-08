package com.example.simple_board.post.service;

import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostDto;
import com.example.simple_board.reply.service.ReplyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostConverter {

    private final ReplyConverter replyConverter;

    public PostDto toDto(PostEntity post) {

        var replyList = post.getReplyList().stream()
                .map(replyConverter::toDto)
                .toList();

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
                .replyList(replyList)
                .build();
    }
}
