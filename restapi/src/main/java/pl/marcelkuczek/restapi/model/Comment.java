package pl.marcelkuczek.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    private long id;
    private String content;
    private long postId;
    private LocalDateTime created;

}