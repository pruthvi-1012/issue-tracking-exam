package au.com.domain.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import au.com.domain.demo.model.Comment;
import au.com.domain.demo.model.Issue;
import au.com.domain.demo.model.User;
import au.com.domain.demo.repository.CommentRepository;
import au.com.domain.demo.repository.IssueRepository;
import au.com.domain.demo.repository.UserRepository;
import au.com.domain.demo.dto.IssueDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/")
    public Issue createIssue(@Valid @RequestBody IssueDto issueDto) {
        User assignee = userRepository.findOne(issueDto.getAssignee().getId());
        User reporter = userRepository.findOne(issueDto.getReporter().getId());

        Issue issue = new Issue(issueDto.getTitle(), issueDto.getDescription(), issueDto.getStatus(), reporter, assignee, issueDto.getCreated(), issueDto.getCompleted());

        return issueRepository.save(issue);   
    }

    @GetMapping("/{id}")
    public IssueDto retrieveIssueById(@PathVariable(value = "id") Long id) {
        Issue issue = issueRepository.findOne(id);
        IssueDto issueDto = new IssueDto(issue.getId(), issue.getTitle(), issue.getDescription(), issue.getStatus(), issue.getCreated(), issue.getCompleted());

        issueDto.setReporter(issue.getReporter() != null ? userRepository.findOne(issue.getReporter().getId()) : null);
        issueDto.setAssignee(issue.getAssignee() != null ? userRepository.findOne(issue.getAssignee().getId()) : null);

        List<Comment> comments = commentRepository.findByIssue(issue);

        issueDto.setComments(comments != null ? comments : null);

        return issueDto;
    }

    @PutMapping("/issue/{id}")
    public Issue updateNote(@PathVariable(value = "id") Long id,
                            @Valid @RequestBody IssueDto issueDto) {

        Issue issue = issueRepository.findOne(id);

        User assignee = userRepository.findOne(issueDto.getAssignee().getId());
        User reporter = userRepository.findOne(issueDto.getReporter().getId());

        issue.setTitle(issueDto.getTitle());
        issue.setDescription(issueDto.getDescription());
        issue.setReporter(reporter);
        issue.setReporter(assignee);
        issue.setStatus(issueDto.getStatus());
        issue.setCreated(issueDto.getCreated());
        issue.setCompleted(issueDto.getCompleted());

        return issueRepository.save(issue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
        Issue issue = issueRepository.findOne(id);
        issueRepository.delete(issue);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
	public Page<IssueDto> retrieveAllIssuesPeagable() {

        List<IssueDto> issueDtos = new ArrayList<>();

        List<Issue> issues = issueRepository.findAll();

         for (Issue issue : issues) {
            IssueDto issueDto = new IssueDto(issue.getId(), issue.getTitle(), issue.getDescription(), issue.getStatus(), issue.getCreated(), issue.getCompleted());

            issueDto.setReporter(issue.getReporter() != null ? userRepository.findOne(issue.getReporter().getId()) : null);
            issueDto.setAssignee(issue.getAssignee() != null ? userRepository.findOne(issue.getAssignee().getId()) : null);
 
            List<Comment> comments = commentRepository.findByIssue(issue);

            issueDto.setComments(comments != null ? comments : null);

            issueDtos.add(issueDto);
         }

 		return new PageImpl<>(issueDtos);
    }
    
    @GetMapping(value = "/assignee/{assigneeId}")
	Page<IssueDto> issuesForAssignee(@PathVariable(value = "assigneeId") Long id){
        User assignee = userRepository.findById(id);
        List<Issue> issues = issueRepository.findByAssignee(assignee);
        List<IssueDto> issueDtos = new ArrayList<>();
        
        for (Issue issue : issues) {
            IssueDto issueDto = new IssueDto(issue.getId(), issue.getTitle(), issue.getDescription(), issue.getStatus(), issue.getCreated(), issue.getCompleted());
            issueDto.setReporter(issue.getReporter() != null ? userRepository.findOne(issue.getReporter().getId()) : null);
            issueDto.setAssignee(issue.getAssignee() != null ? userRepository.findOne(issue.getAssignee().getId()) : null);
 
            List<Comment> comments = commentRepository.findByIssue(issue);

            issueDto.setComments(comments != null ? comments : null);

            issueDtos.add(issueDto);
        }
	    return new PageImpl<>(issueDtos);
    }

    @GetMapping(value = "/reporter/{reporterId}")
	Page<IssueDto> issuesForReporter(@PathVariable(value = "reporterId") Long id){
        User reporter = userRepository.findById(id);
        List<Issue> issues = issueRepository.findByAssignee(reporter);
        List<IssueDto> issueDtos = new ArrayList<>();
        
        for (Issue issue : issues) {
            IssueDto issueDto = new IssueDto(issue.getId(), issue.getTitle(), issue.getDescription(), issue.getStatus(), issue.getCreated(), issue.getCompleted());
            issueDto.setReporter(issue.getReporter() != null ? userRepository.findOne(issue.getReporter().getId()) : null);
            issueDto.setAssignee(issue.getAssignee() != null ? userRepository.findOne(issue.getAssignee().getId()) : null);
 
            List<Comment> comments = commentRepository.findByIssue(issue);

            issueDto.setComments(comments != null ? comments : null);

            issueDtos.add(issueDto);
        }
	    return new PageImpl<>(issueDtos);
    }
    
    @GetMapping(value = "/issues/{startDate}/{endDate}")
    Page<IssueDto> issuesBetweenDateCreated(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable(value = "startDate") Date startDate, 
                                         @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable(value = "endDate") Date endDate ){
        List<Issue> issues = issueRepository.findByCreatedBetween(startDate, endDate);
        List<IssueDto> issueDtos = new ArrayList<>();
        for (Issue issue : issues) {
            IssueDto issueDto = new IssueDto(issue.getId(), issue.getTitle(), issue.getDescription(), issue.getStatus(), issue.getCreated(), issue.getCompleted());
            issueDto.setReporter(issue.getReporter() != null ? userRepository.findOne(issue.getReporter().getId()) : null);
            issueDto.setAssignee(issue.getAssignee() != null ? userRepository.findOne(issue.getAssignee().getId()) : null);
 
            List<Comment> comments = commentRepository.findByIssue(issue);

            issueDto.setComments(comments != null ? comments : null);

            issueDtos.add(issueDto);
        }
	    return new PageImpl<>(issueDtos);
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
