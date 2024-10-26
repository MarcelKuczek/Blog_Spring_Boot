package pl.marcelkuczek.restapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.marcelkuczek.restapi.model.Post;
import pl.marcelkuczek.restapi.repository.PostRepository;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private static final int PageSize = 20;
    private final PostRepository postRepository;

    public List<Post> getPosts(int page){
        return postRepository.findAllPosts(PageRequest.of(page,PageSize));
    }
    public Post getSinglePost(long id){
        return postRepository.findById(id)
            .orElseThrow();
    }
}
