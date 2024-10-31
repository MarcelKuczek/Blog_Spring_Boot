package pl.marcelkuczek.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;

    @OneToMany
    @JoinColumn(name = "postId")
    private List<Comment> comment;
}
