package au.com.domain.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import au.com.domain.demo.model.Issue;
import au.com.domain.demo.model.User;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long>{
    List<Issue> findByAssignee(User asignee);
    List<Issue> findByReporter(User reporter);
    List<Issue> findByStatus(Long status);
    List<Issue> findByCreatedBetween(Date start, Date end);
    List<Issue> findAllByOrderByCreatedAsc();
    List<Issue> findAllByOrderByCreatedDesc();
}