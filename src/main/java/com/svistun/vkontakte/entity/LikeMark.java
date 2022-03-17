package com.svistun.vkontakte.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "like_mark", schema = "public")
public class LikeMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "user_id")
    @JsonIgnore
    private Long userId;

    @Column(name = "post_id")
    private Long postId;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false, insertable = false)
    private User user;


    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Post post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LikeMark likeMark = (LikeMark) o;
        return id != null && Objects.equals(id, likeMark.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
