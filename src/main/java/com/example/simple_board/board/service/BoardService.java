package com.example.simple_board.board.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.db.BoardRepository;
import com.example.simple_board.board.model.BoardDto;
import com.example.simple_board.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;


    public BoardDto createBoard(BoardRequest boardRequest) {

        var entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();

        var savedEntity = boardRepository.save(entity);
        return boardConverter.toDto(savedEntity);
    }

    public BoardDto view(Long id) {

        log.info("entity {}", boardRepository.findById(id).get());
        var entity = boardRepository.findById(id).get();
        return boardConverter.toDto(entity);
    }
}
