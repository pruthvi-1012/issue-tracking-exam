package au.com.domain.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.domain.demo.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    Set<Comment> findByIssue(Long issueId);
}