package com.svistun.vkontakte.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "post", schema = "public")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @Column(name = "id_creator")
    private Long creatorId;

    @ManyToOne
    @JoinColumn(name = "id_creator", insertable = false, updatable = false)
    private User creator;

    @OneToOne
    @JoinColumn(name = "media", unique = true, nullable = false, updatable = false)
    private MediaFile media;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private List<Comments> comments;

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private List<LikeMark> likeMarks;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Post post = (Post) o;
        return id != null && Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
