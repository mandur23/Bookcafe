package com.example.bookcafe.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private int id;
    private String title;
    private String content;
    private String author;
    private String writer;
    private String publisher;
}
