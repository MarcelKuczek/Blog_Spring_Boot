package pl.marcelkuczek.restapi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.marcelkuczek.restapi.model.Comment;

/**
 * Repository interface for accessing Comment entities in the database.
 * This interface extends JpaRepository to provide basic CRUD operations
 * for Comment entities.
 *
 * @author Marcel Kuczek
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Retrieves all comments that are associated with the specified list of post IDs.
     *
     * @param ids List of post IDs for which to retrieve comments
     * @return List of comments associated with the specified post IDs
     */
    List<Comment> findAllByPostIdIn(List<Long> ids);
}
