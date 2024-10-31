package pl.marcelkuczek.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.marcelkuczek.restapi.controller.dto.PostDto;
import pl.marcelkuczek.restapi.model.Post;
import pl.marcelkuczek.restapi.service.PostService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam(required = false) int page){
        int pageNumber = page > 0 ? page : 1;
        return PostDtoMapper.mapToPostDto(postService.getPosts(pageNumber - 1));
    }

    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) int page){
        int pageNumber = page > 0 ? page : 1;
        return postService.getPostsWithComments(pageNumber - 1);
    }


    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id){
        return postService.getSinglePost(id);
    }
}
