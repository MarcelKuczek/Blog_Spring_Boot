package pl.marcelkuczek.restapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.springframework.test.web.servlet.MockMvc;
import pl.marcelkuczek.restapi.repository.PostRepository;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private PostRepository postRepository;

    @Test
    void shouldGetSinglePost() throws Exception{
        mockmvc.perform(MockMvcRequestBuilders.get("/posts/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect((MockMvcResultMatchers.status().isOk()))
                .andExpect(jsonPath("$.title", not(emptyOrNullString())))
                .andExpect(jsonPath("$.content", not(emptyOrNullString())));

    }
    @Test
    void shouldGetPaginatedPosts() throws Exception{
        mockmvc.perform(MockMvcRequestBuilders.get("/posts?page=1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect((MockMvcResultMatchers.status().isOk()))
                .andExpect(jsonPath("$", hasSize(lessThanOrEqualTo(10))));
    }
    @Test
    void shouldAddNewPost() throws Exception {
        String newPostJson = """
            {
                "author": "Marcel Kuczek",
                "title": "New Post Title",
                "content": "This is the content of the new post."
            }
        """;

        mockmvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPostJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.author", is("Marcel Kuczek")))
                .andExpect(jsonPath("$.title", is("New Post Title")))
                .andExpect(jsonPath("$.content", is("This is the content of the new post.")));
    }
    @Test
    void shouldEditExistingPost() throws Exception {
        String updatedPostJson = """
            {
                "id": 2,
                "title": "Updated Post Title",
                "content": "Updated content of the post."
            }
        """;

        mockmvc.perform(MockMvcRequestBuilders.put("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPostJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.title", is("Updated Post Title")))
                .andExpect(jsonPath("$.content", is("Updated content of the post.")));
    }

    @Test
    void shouldDeletePost() throws Exception {
        mockmvc.perform(MockMvcRequestBuilders.delete("/posts/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        boolean postExists = postRepository.existsById(1L);
        assertFalse(postExists, "Post should be deleted from the database");
    }

}