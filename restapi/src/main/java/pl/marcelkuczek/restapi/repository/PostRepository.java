package pl.marcelkuczek.restapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.marcelkuczek.restapi.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing Post entities in the database.
 * This interface extends JpaRepository to provide basic CRUD operations
 * for Post entities. It also includes custom query methods for retrieving
 * posts along with their associated comments.
 *
 * @author Marcel Kuczek
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Retrieves all posts from the database, including their associated comments.
     *
     * @param page Pageable object to control pagination information
     * @return List of posts with their comments
     */
    @Query("SELECT p FROM Post p LEFT JOIN fetch p.comment")
    List<Post> findAllPosts(Pageable page);
}
