package com.example.simple_board.post.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.db.BoardRepository;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.db.PostRepository;
import com.example.simple_board.post.model.PostDto;
import com.example.simple_board.post.model.PostRequest;
import com.example.simple_board.post.model.PostViewRequest;
import com.example.simple_board.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ReplyService replyService;
    private final BoardRepository boardRepository;
    private final PostConverter postConverter;

    public PostDto create(PostRequest postRequest) {

        var theBoard = boardRepository.findById(postRequest.getBoardId()).get();
        var entity = PostEntity.builder()
                .board(theBoard) // 임시 고정
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();

        var savedEntity = postRepository.save(entity);
        return postConverter.toDto(savedEntity);
    }

    public PostDto view(PostViewRequest request) {

        return postRepository.findFirstByIdAndStatusOrderByIdDesc(request.getPostId(), "REGISTERED")
                .map(it -> {
                    if (!it.getPassword().equals(request.getPassword())) {
                        var format = "패스워드가 맞지 않습니다. %s vs %s";
                        throw new RuntimeException(String.format(format, it.getPassword(), request.getPassword()));
                    }

                    return postConverter.toDto(it);

                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재하지 않습니다. : " + request.getPostId());
                        }
                );
    }

    public List<PostEntity> all() {

        return postRepository.findAll();
    }

    public void delete(PostViewRequest request) {

        postRepository.findById(request.getPostId())
                .map(it -> {
                    if (!it.getPassword().equals(request.getPassword())) {
                        var format = "패스워드가 맞지 않습니다. %s vs %s";
                        throw new RuntimeException(String.format(format, it.getPassword(), request.getPassword()));
                    }

                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);
                    return it;

                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재하지 않습니다. ID : " + request.getPostId());
                        }
                );
    }
}
