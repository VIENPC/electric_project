package com.nhutin.electric_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRequest {

	private String receiverId;
    private int replyId;
    private String replyContent;
    private int commentId;
    private int postId;
}
