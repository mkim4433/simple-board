package com.example.simple_board.board.controller;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardRequest;
import com.example.simple_board.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("")
    public BoardEntity create(@Valid @RequestBody BoardRequest boardRequest) {

        return boardService.createBoard(boardRequest);
    }
}
