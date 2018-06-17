package au.com.domain.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import au.com.domain.demo.model.Issue;
import au.com.domain.demo.services.IssueServices;
import au.com.domain.demo.services.UserServices;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    IssueServices issueServices;

	@GetMapping("/issue/all")
	public List<Issue> retrieveAllIssues() {
		return issueServices.findAll();
    }

    @GetMapping("/issue/{id}")
    public Issue retrieveIssueById(@PathVariable(value = "id") Long id) {
        return issueServices.findOne(id);
    }
    
    @DeleteMapping("/issue/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
        Issue issue = issueServices.findOne(id);
        issueServices.delete(issue);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/issue")
    public Issue createIssue(@Valid @RequestBody Issue issue) {
        return issueServices.save(issue);   
    }

    @PutMapping("/issue/{id}")
    public Issue updateNote(@PathVariable(value = "id") Long id,
                                        @Valid @RequestBody Issue issueDetails) {

    Issue issue = issueServices.findOne(id);
  //          .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

    issue.setTitle(issueDetails.getTitle());
    issue.setDescription(issueDetails.getDescription());
    issue.setReporter(issueDetails.getAsignee());
    issue.setReporter(issueDetails.getReporter());
    issue.setStatus(issueDetails.getStatus());
    issue.setCreated(issueDetails.getCreated());
    issue.setCompleted(issueDetails.getCompleted());

    Issue updatedIssue = issueServices.save(issue);
    return updatedIssue;
}

    @RequestMapping(value = "/issuesPeagable", method = RequestMethod.GET)
	Page<Issue> list( Pageable pageable ){
		Page<Issue> issues = issueServices.findAll(pageable);
	return issues;
	} 

}
