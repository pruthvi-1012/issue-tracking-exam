package au.com.domain.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import au.com.domain.demo.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long>{
    List<Issue> findByAsignee(Long asignee);
    List<Issue> findByReporter(Long reporter);
    List<Issue> findByStatus(Long status);
    List<Issue> findByCreatedBetween(Date start, Date end);
   // Page<Issue> findAllOrderByCreatedAsc(Pageable pageable);
   // Page<Issue> findAllOrderByCreatedDesc(Pageable pageable);
}