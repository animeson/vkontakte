package com.svistun.vkontakte.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "comments", schema = "public")
public class Comments {
    @Id
    @JoinColumn(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "comment", length = 100)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "author", insertable = false, updatable = false)
    private User author;

    @JsonIgnore
    @Column(name = "author")
    private Long authorId;


    @JoinColumn(name = "date")
    private LocalDateTime date;

    @JsonIgnore
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Post post;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comments comments = (Comments) o;
        return id != null && Objects.equals(id, comments.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", author=" + author +
                ", authorId=" + authorId +
                ", date=" + date +
                '}';
    }
}
