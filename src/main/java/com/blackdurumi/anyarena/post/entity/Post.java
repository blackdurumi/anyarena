package com.blackdurumi.anyarena.post.entity;

import com.blackdurumi.anyarena.account.entity.Account;
import com.blackdurumi.anyarena.account.entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor
@Table(
    name = "post"
)
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    @Builder.Default
    @ColumnDefault("0")
    @Column(name = "views", nullable = false)
    private Long views = 0L;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_liker",
        joinColumns = @JoinColumn(name = "postId"),
        inverseJoinColumns = @JoinColumn(name = "accountId"))
    private List<Account> likers = new ArrayList<Account>();

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account poster;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
