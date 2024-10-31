package pl.marcelkuczek.restapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.marcelkuczek.restapi.model.Comment;
import pl.marcelkuczek.restapi.model.Post;
import pl.marcelkuczek.restapi.repository.CommentRepository;
import pl.marcelkuczek.restapi.repository.PostRepository;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private static final int pageSize = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Post> getPosts(int pageNumber) {
        return postRepository.findAllPosts(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc("created"))));
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id)
                .orElseThrow();
    }

    public List<Post> getPostsWithComments(int pageNumber) {
        List<Post> allPosts = postRepository.findAllPosts(PageRequest.of(pageNumber, pageSize));
        List<Long> ids = allPosts.stream()
                .map(Post::getId)
                .collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        allPosts.forEach(post -> post.setComment(extractComments(comments, post.getId())));
        return allPosts;
    }

    private List<Comment> extractComments(List<Comment> comments, long id){
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList());
    }
}
