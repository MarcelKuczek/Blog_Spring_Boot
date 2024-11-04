package pl.marcelkuczek.restapi.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a Post in the REST API.
 * This class is mapped to a database entity and includes attributes related to a post,
 * including its title, content, creation date, and associated comments.
 *
 * @author Marcel Kuczek
 */
@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Author;
    private String title;
    private String content;
    private LocalDateTime created = LocalDateTime.now();
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "postId", updatable = false, insertable = false)
    private List<Comment> comment;

}
