package com.example.bookcafe.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private int id; // 게시물 ID
    private String title; // 게시물 제목
    private String content; // 게시물 내용
    private String author; // 작성자 이름
    private String publisher; // 출판사
    private String comment;
}
