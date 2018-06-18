package au.com.domain.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import au.com.domain.demo.model.Issue;
import au.com.domain.demo.repository.CommentRepository;
import au.com.domain.demo.repository.IssueRepository;
// import au.com.domain.demo.services.IssueServices;
import au.com.domain.demo.services.UserServices;
import au.com.domain.demo.daos.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class HelloController {

	// UserServices userServices;
    @Autowired
    IssueRepository issueRepository;

    @Autowired
    UserServices userRepository;

    @Autowired
    CommentRepository commentRepository;

	@GetMapping("/issue/all")
	public List<Issue> retrieveAllIssues() {
		return issueRepository.findAll();
    }

    @GetMapping("/issues/all")
	public Set<IssueDao> retrieveAllIssues1() {

        Set<IssueDao> issues = new HashSet<>();

        List<Issue> listOfIssues = issueRepository.findAll();

        for (Issue issue : listOfIssues) {
            IssueDao issueDao = new IssueDao();
            issueDao.setId(issue.getId());
            issueDao.setAssignee(userRepository.findOne(issue.getAssignee().getId()));
           // issueDao.setReporter(userRepository.findById(issue.getReporter().getId()));
            //issueDao.setComments(commentRepository.findAll(issue.getId()))
            issueDao.setDescription(issue.getDescription());
            issueDao.setCreated(issue.getCreated());
            issueDao.setCompleted(issue.getCompleted());
            issueDao.setStatus(issue.getStatus());
            issueDao.setTitle(issue.getTitle());

            issues.add(issueDao);
        }

        // listOfIssues.forEach(issue -> {
 
        // });

 		return issues;
    }

    @GetMapping("/issue/{id}")
    public Issue retrieveIssueById(@PathVariable(value = "id") Long id) {
        return issueRepository.findOne(id);
    }
    
    @DeleteMapping("/issue/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
        Issue issue = issueRepository.findOne(id);
        issueRepository.delete(issue);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/issue")
    public Issue createIssue(@Valid @RequestBody Issue issue) {
        return issueRepository.save(issue);   
    }

    @PutMapping("/issue/{id}")
    public Issue updateNote(@PathVariable(value = "id") Long id,
                                        @Valid @RequestBody Issue issueDetails) {

    Issue issue = issueRepository.findOne(id);
  //          .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

    issue.setTitle(issueDetails.getTitle());
    issue.setDescription(issueDetails.getDescription());
    issue.setReporter(issueDetails.getAssignee());
    issue.setReporter(issueDetails.getReporter());
    issue.setStatus(issueDetails.getStatus());
    issue.setCreated(issueDetails.getCreated());
    issue.setCompleted(issueDetails.getCompleted());

    Issue updatedIssue = issueRepository.save(issue);
    return updatedIssue;
}

    @RequestMapping(value = "/issuesPeagable", method = RequestMethod.GET)
	Page<Issue> list( Pageable pageable ){
		Page<Issue> issues = issueRepository.findAll(pageable);
	return issues;
    }
    
    @GetMapping(value = "/issuesForAssignee/{assigneeId}")
	List<Issue> issuesForAssignee(@PathVariable(value = "assigneeId") Long id){
		List<Issue> issues = issueRepository.findByAssignee(id);
	return issues;
    }
    
    @GetMapping(value = "/issues/{startDate}/{endDate}")
    List<Issue> issuesBetweenDateCreated(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable(value = "startDate") Date startDate, 
                                         @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable(value = "endDate") Date endDate ){
        List<Issue> issues = issueRepository.findByCreatedBetween(startDate, endDate);
    return issues;
    }

    // @GetMapping(value = "/issue/ascending")
	// Page<Issue> getByCreatedDateAssending(Pageable pageable){
	// 	Page<Issue> issues = issueRepository.findAllOrderByCreatedAsc(pageable);
	// return issues;
    // }

    // @GetMapping(value = "/issue/descending")
	// Page<Issue> getByCreatedDateDescending(Pageable pageable){
	// 	Page<Issue> issues = issueRepository.findAllOrderByCreatedDesc(pageable);
	// return issues;
    //}

}
