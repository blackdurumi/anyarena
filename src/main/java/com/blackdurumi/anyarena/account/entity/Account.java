package com.blackdurumi.anyarena.account.entity;

import com.blackdurumi.anyarena.post.entity.Comment;
import com.blackdurumi.anyarena.post.entity.Post;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(
    name = "account",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_account_identity", columnNames = "identity")
    }
)
@Builder
@Getter
@AllArgsConstructor
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "identity", unique = true)
    private String identity;

    @Column(name = "encryptedPassword")
    private String encryptedPassword;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToMany(mappedBy = "commenter")
    private List<Comment> comments;

    @OneToMany(mappedBy = "poster")
    private List<Post> posts;
}
