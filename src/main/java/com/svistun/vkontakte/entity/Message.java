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
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "message", schema = "public")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "message")
    private String message;
    @Column(name = "date_time_sending")
    private LocalDateTime dataTimeSendingMessages;

    @JsonIgnore
    @Column(name = "sender")
    private Long senderById;

    @JsonIgnore
    @Column(name = "recipient")
    private Long recipientById;


    @ManyToOne
    @JoinColumn (name="sender",insertable = false, updatable = false)
    private User sender;

    @ManyToOne
    @JoinColumn (name="recipient",insertable = false, updatable = false)
    private User recipient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Message message = (Message) o;
        return id != null && Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
