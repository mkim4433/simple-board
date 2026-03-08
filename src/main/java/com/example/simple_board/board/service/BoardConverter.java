package com.example.simple_board.board.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardDto;
import com.example.simple_board.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardConverter {

    private final PostConverter postConverter;

    public BoardDto toDto(BoardEntity board) {

        var postList = board.getPostList()
                .stream()
                .map(postConverter::toDto)
                .toList();

        return BoardDto.builder()
                .id(board.getId())
                .boardName(board.getBoardName())
                .status(board.getStatus())
                .postList(postList)
                .build();
    }
}
