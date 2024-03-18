package com.blackdurumi.anyarena.post.entity;


import com.blackdurumi.anyarena.account.entity.Account;
import com.blackdurumi.anyarena.account.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor
@Table(
    name = "comment"
)
@Builder
@Getter
@AllArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "content")
    private String content;

    @Builder.Default
    @ColumnDefault("0")
    @Column(name = "likes")
    private Long likes = 0L;

    @ManyToOne()
    private Account commenter;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
}
