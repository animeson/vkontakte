package com.svistun.vkontakte.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "media", schema = "public")
public class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "patch")
    private String patch;
    @Column(name = "type", length = 15)
    private String type;
    @Column(name = "file_size")
    private double fileSize;
    @Column(name = "upload_date")
    private LocalDateTime uploadDate;
    @Column(name = "hash_file")
    private Integer hashFile;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MediaFile mediaFile = (MediaFile) o;
        return id != null && Objects.equals(id, mediaFile.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
