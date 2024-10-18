// 이 클래스는 게시판 정보를 나타내는 엔티티입니다.
// JPA를 사용하여 데이터베이스의 'board_table'과 매핑됩니다.

package com.example.bookcafe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // 이 클래스가 JPA 엔티티임을 나타냄
@Getter // Lombok을 사용하여 getter 메서드 자동 생성
@Setter // Lombok을 사용하여 setter 메서드 자동 생성
@Table(name = "board_table") // 데이터베이스의 'board_table'과 매핑
public class BoardEntity {
    @Id // 이 필드가 기본 키임을 나타냄
    @GeneratedValue // 기본 키의 값 자동 생성
    private int id; // 게시글 ID

    @Column // 제목 필드
    private String title; // 게시글 제목

    @Column // 내용 필드
    private String content; // 게시글 내용

    @Column // 작성자 필드
    private String author; // 게시글 작성자

    @Column // 댓글 필드
    private String comment; // 게시글 댓글
}
