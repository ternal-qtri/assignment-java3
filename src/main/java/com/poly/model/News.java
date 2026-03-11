package com.poly.model;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class News {
    private String id;
    private String title;
    private String content;
    private String image;

    private Date postedDate;
    private int authorId;
    private String authorName;
    private int viewCount;

    private String categoryId;
    private boolean home;
}
