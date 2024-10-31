package pl.marcelkuczek.restapi.controller;

import pl.marcelkuczek.restapi.controller.dto.PostDto;
import pl.marcelkuczek.restapi.model.Post;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for mapping Post entities to PostDto objects.
 * This class contains static methods to convert between Post
 * and PostDto representations.
 *
 * @author Marcel Kuczek
 */
public class PostDtoMapper {

    private PostDtoMapper() {
    }

    /**
     * Maps a list of Post entities to a list of PostDto objects.
     *
     * @param posts the list of Post entities to map
     * @return a list of mapped PostDto objects
     */
    public static List<PostDto> mapToPostDto(List<Post> posts) {
        return posts.stream()
                .map(PostDtoMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    /**
     * Maps a single Post entity to a PostDto object.
     *
     * @param post the Post entity to map
     * @return the mapped PostDto object
     */
    public static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .created(post.getCreated())
                .build();
    }
}
