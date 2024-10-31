package pl.marcelkuczek.restapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.marcelkuczek.restapi.model.Comment;
import pl.marcelkuczek.restapi.model.Post;
import pl.marcelkuczek.restapi.repository.CommentRepository;
import pl.marcelkuczek.restapi.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing posts and comments.
 * This class contains business logic for handling operations related to posts.
 *
 * @author Marcel Kuczek
 */
@RequiredArgsConstructor
@Service
public class PostService {

    private static final int pageSize = 10;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    /**
     * Retrieves a paginated list of posts.
     *
     * @param pageNumber the page number to retrieve (0-based index)
     * @return a list of Post objects representing the posts
     */
    public List<Post> getPosts(int pageNumber) {
        return postRepository.findAllPosts(PageRequest.of(pageNumber, pageSize));
    }

    /**
     * Retrieves a single post by its ID.
     *
     * @param id the ID of the post to retrieve
     * @return the Post object representing the requested post
     * @throws EntityNotFoundException if the post with the given ID does not exist
     */
    public Post getSinglePost(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with ID: " + id));
    }

    /**
     * Retrieves a paginated list of posts along with their comments.
     *
     * @param pageNumber the page number to retrieve (0-based index)
     * @return a list of Post objects representing the posts with their comments
     */
    public List<Post> getPostsWithComments(int pageNumber) {
        List<Post> allPosts = postRepository.findAllPosts(PageRequest.of(pageNumber, pageSize));
        List<Long> ids = allPosts.stream()
                .map(Post::getId)
                .collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        allPosts.forEach(post -> post.setComment(extractComments(comments, post.getId())));
        return allPosts;
    }

    /**
     * Extracts comments associated with a specific post ID.
     *
     * @param comments the list of all comments
     * @param id the ID of the post whose comments are to be extracted
     * @return a list of Comment objects associated with the given post ID
     */
    private List<Comment> extractComments(List<Comment> comments, long id) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList());
    }
}
