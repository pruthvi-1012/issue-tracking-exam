package au.com.domain.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import au.com.domain.demo.model.Comment;
import au.com.domain.demo.model.Issue;
import au.com.domain.demo.repository.CommentRepository;
import au.com.domain.demo.repository.IssueRepository;
// import au.com.domain.demo.services.IssueServices;
import au.com.domain.demo.services.UserServices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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
public class CommentController {

    
    // UserServices userServices;
    @Autowired
    CommentRepository commentRepository;

	@GetMapping("/comment/all")
	public List<Comment> retrieveAllComments() {
		return commentRepository.findAll();
    }
    
    @GetMapping("/comment/{id}")
	public Comment retriveCommentById(@PathVariable(value = "id") Long id) {
		return commentRepository.findOne(id);
    }
    
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable(value = "id") Long id) {
        Comment comment = commentRepository.findOne(id);
        commentRepository.delete(comment);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comment")
    public Comment createComment(@Valid @RequestBody Comment comment) {  
        comment.setCreated(new Date());
        return commentRepository.save(comment);   
    }

    // @RequestMapping(value = "/issuesPeagable", method = RequestMethod.GET)
	// Page<Issue> list( Pageable pageable ){
	// 	Page<Issue> issues = issueRepository.findAll(pageable);
	// return issues;
    // }
    
    // @GetMapping(value = "/issuesForAssignee/{assigneeId}")
	// List<Issue> issuesForAssignee(@PathVariable(value = "assigneeId") Long id){
	// 	List<Issue> issues = issueRepository.findByAssignee(id);
	// return issues;
    // }
    
    // @GetMapping(value = "/issues/{startDate}/{endDate}")
    // List<Issue> issuesBetweenDateCreated(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable(value = "startDate") Date startDate, 
    //                                      @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable(value = "endDate") Date endDate ){
    //     List<Issue> issues = issueRepository.findByCreatedBetween(startDate, endDate);
    // return issues;
    // }

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
