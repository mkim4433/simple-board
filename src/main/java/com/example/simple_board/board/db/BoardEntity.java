package com.example.simple_board.board.db;

import com.example.simple_board.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardName;

    private String status;

    @OneToMany(mappedBy = "board")
    @SQLRestriction("status = 'REGISTERED'")
    @OrderBy("id desc")
    @Builder.Default
    private List<PostEntity> postList = List.of();
}
