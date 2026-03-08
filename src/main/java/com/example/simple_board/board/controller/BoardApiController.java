package com.example.simple_board.board.controller;

import com.example.simple_board.board.model.BoardDto;
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
    public BoardDto create(@Valid @RequestBody BoardRequest boardRequest) {

        return boardService.createBoard(boardRequest);
    }

    @GetMapping("/{id}")
    public BoardDto view(@PathVariable Long id) {

        return boardService.view(id);
    }
}
