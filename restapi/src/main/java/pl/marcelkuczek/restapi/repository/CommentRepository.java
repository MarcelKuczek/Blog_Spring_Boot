package pl.marcelkuczek.restapi.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.marcelkuczek.restapi.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment>findAllByPostIdIn(List<Long> ids);
}
