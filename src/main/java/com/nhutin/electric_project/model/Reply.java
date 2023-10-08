package com.nhutin.electric_project.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Replys")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private int replyId;

    @Column(name = "reply_content", columnDefinition = "TEXT")
    private String replyContent;

    @ManyToOne
    @JoinColumn(name = "responder_id")
    private User responder;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @Column(name = "reply_date")
    private Date replyDate;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    // getters and setters
}