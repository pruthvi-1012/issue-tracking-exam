package au.com.domain.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.domain.demo.model.Comment;
import au.com.domain.demo.model.Issue;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByIssue(Issue issue);
}