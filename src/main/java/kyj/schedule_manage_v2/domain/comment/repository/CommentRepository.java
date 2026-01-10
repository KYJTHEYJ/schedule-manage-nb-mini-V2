package kyj.schedule_manage_v2.domain.comment.repository;

import kyj.schedule_manage_v2.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
