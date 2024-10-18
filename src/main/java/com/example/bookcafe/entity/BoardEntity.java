package com.example.bookcafe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String author;
    @Column
    private String comment;
}
