package com.nhutin.electric_project.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int comment_id;
    private Date comment_date;
    private String user_id;
    private int product_id;
    private String content;

}
