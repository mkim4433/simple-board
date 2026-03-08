package com.example.simple_board.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Pagination {

    private Integer page;

    private Integer size;

    private Integer currentElements;

    private Integer totalPages;

    private Long totalElements;

}
