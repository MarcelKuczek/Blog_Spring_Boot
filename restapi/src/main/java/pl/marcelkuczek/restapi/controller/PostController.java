package pl.marcelkuczek.restapi.controller;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.marcelkuczek.restapi.controller.dto.PostDto;
import pl.marcelkuczek.restapi.model.Post;
import pl.marcelkuczek.restapi.service.PostService;

import java.util.List;

/**
 * REST controller for managing posts.
 * This class handles HTTP requests related to posts and delegates the
 * business logic to the PostService.
 *
 * @author Marcel Kuczek
 */
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    /**
     * Retrieves a paginated list of posts.
     *
     * @param page the page number to retrieve
     * @return a list of PostDto objects representing the posts
     */
    @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam(required = false) Integer page) {
        int pageNumber = page != null && page > 0 ? page : 1;
        return PostDtoMapper.mapToPostDto(postService.getPosts(pageNumber - 1));
    }

    /**
     * Retrieves a paginated list of posts along with their comments.
     *
     * @param page the page number to retrieve
     * @return a list of Post objects representing the posts with comments
     */
    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) Integer page) {
        int pageNumber = page != null && page > 0 ? page : 1;
        return postService.getPostsWithComments(pageNumber - 1);
    }

    /**
     * Retrieves a single post by its ID.
     *
     * @param id the ID of the post to retrieve
     * @return the Post object representing the requested post
     */
    @GetMapping("/posts/{id}")
    public Post getSinglPost(@PathVariable Long id) {
        return postService.getSinglePost(id);
    }

    /**
     * Adds a new post.
     *
     * @param post the Post object to be added
     * @return the added Post object
     */
    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    /**
     * Edits an existing post.
     *
     * @param post the Post object with updated information
     * @return the updated Post object
     */
    @PutMapping("/posts")
    public Post editPost(@RequestBody Post post) {
        return postService.editPost(post);
    }

    /**
     * Deletes a post by its ID.
     *
     * @param id the ID of the post to be deleted
     */
    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }
}