package pl.marcelkuczek.restapi.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing a Post.
 * This class is used to transfer post data between layers of the application.
 *
 * @author Marcel Kuczek
 */
@Getter
@Builder
public class PostDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime created;
}
