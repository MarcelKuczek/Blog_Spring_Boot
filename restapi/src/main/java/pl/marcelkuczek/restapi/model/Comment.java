package pl.marcelkuczek.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


/**
 * Represents a Comment in the REST API.
 * This class is mapped to a database entity and includes attributes related to a comment,
 * including its content, associated post ID, and creation timestamp.
 *
 * @author Marcel Kuczek
 */
@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long postId;
    private LocalDateTime created;
}