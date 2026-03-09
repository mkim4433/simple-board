package com.example.simple_board.post.service;

import com.example.simple_board.board.db.BoardRepository;
import com.example.simple_board.crud.Converter;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostDto;
import com.example.simple_board.reply.service.ReplyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostConverter implements Converter<PostDto, PostEntity> {

    private final ReplyConverter replyConverter;
    private final BoardRepository boardRepository;

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

    public PostEntity toEntity(PostDto postDto) {

        var boardEntity = boardRepository.findById(postDto.getBoardId());

        return PostEntity.builder()
                .id(postDto.getId())
                .board(boardEntity.orElse(null))
                .userName(postDto.getUserName())
                .password(postDto.getPassword())
                .email(postDto.getEmail())
                .status(postDto.getStatus() != null ? postDto.getStatus() : "REGISTERED")
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .postedAt(postDto.getPostedAt() != null ? postDto.getPostedAt() : LocalDateTime.now())
                .build();
    }
}
