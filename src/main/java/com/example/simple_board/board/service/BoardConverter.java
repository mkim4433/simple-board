package com.example.simple_board.board.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardDto;
import com.example.simple_board.crud.Converter;
import com.example.simple_board.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardConverter implements Converter<BoardDto, BoardEntity> {

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

    public  BoardEntity toEntity(BoardDto boardDto) {

        return BoardEntity.builder()
                .id(boardDto.getId())
                .boardName(boardDto.getBoardName())
                .status(boardDto.getStatus() != null ? boardDto.getStatus() : "REGISTERED")
                .build();
    }
}
