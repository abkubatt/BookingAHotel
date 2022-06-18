package com.example.bookinghotel.models.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "tb_reply_to_review")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReplyToReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    LocalDate date;
    @OneToOne
    @JoinColumn(name = "review_id")
    Review review;
}
