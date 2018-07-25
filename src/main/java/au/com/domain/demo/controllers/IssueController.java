package au.com.domain.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import au.com.domain.demo.model.Comment;
import au.com.domain.demo.model.Issue;
import au.com.domain.demo.model.User;
import au.com.domain.demo.repository.CommentRepository;
import au.com.domain.demo.repository.IssueRepository;
import au.com.domain.demo.repository.UserRepository;
import au.com.domain.demo.services.IssueTrackerService;
import au.com.domain.demo.dto.IssueDto;
import au.com.domain.demo.exceptions.IssueNotfoundException;
import au.com.domain.demo.exceptions.UserNotfoundException;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    IssueTrackerService issueTrackerService;

    @PostMapping("/")
    public Issue createIssue(@Valid @RequestBody IssueDto issueDto) {
        User assignee = userRepository.findOne(issueDto.getAssignee().getId());
        User reporter = userRepository.findOne(issueDto.getReporter().getId());

        Issue issue = new Issue(issueDto.getTitle(), issueDto.getDescription(), issueDto.getStatus(), reporter, assignee);

        issue.setCreated(new Date());

        return issueRepository.save(issue);   
    }

    @GetMapping("/{id}")
    public IssueDto retrieveIssueById(@PathVariable(value = "id") Long id) {
        Issue issue = issueRepository.findOne(id);

        if (issue == null){
            throw new IssueNotfoundException("issue id : " + id);
        }
        return issueTrackerService.convertIssueToIssueDTO(issue);
    }

    @PutMapping("/{id}")
    public Issue updateNote(@PathVariable(value = "id") Long id,
                            @Valid @RequestBody IssueDto issueDto) {

        Issue issue = issueRepository.findOne(id);

        if (issue == null){
            throw new IssueNotfoundException("issue id : " + id);
        }

        User assignee = userRepository.findOne(issueDto.getAssignee().getId());
        User reporter = userRepository.findOne(issueDto.getReporter().getId());

        issue.setTitle(issueDto.getTitle());
        issue.setDescription(issueDto.getDescription());
        issue.setReporter(reporter);
        issue.setReporter(assignee);
        issue.setStatus(issueDto.getStatus());
        issue.setCompleted(new Date());

        return issueRepository.save(issue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
        Issue issue = issueRepository.findOne(id);

        if (issue == null){
            throw new IssueNotfoundException("issue id : " + id);
        }

        List<Comment> comments = commentRepository.findByIssue(issue);

        // Delete all comments related to issue before deleting issue

        for (Comment c : comments) {
            Comment comment = commentRepository.findOne(c.getId());
            commentRepository.delete(comment);
        }
        
        issueRepository.delete(issue);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
	public Page<IssueDto> retrieveAllIssuesPeagable(Pageable pageable) {

        Page<Issue> issues = issueRepository.findAll(pageable);

        List<IssueDto> issueDtos = issues.getContent().stream().map(issue -> issueTrackerService.convertIssueToIssueDTO(issue)).collect(Collectors.toList());
        
        final int currentTotal=pageable.getOffset() + pageable.getPageSize();

 		return new PageImpl<>(issueDtos,pageable,currentTotal);
    }
    
    @GetMapping(value = "/assignee/{assigneeId}")
	List<IssueDto> issuesForAssignee(@PathVariable(value = "assigneeId") Long id){

        User assignee = userRepository.findById(id);

        if (assignee == null) {
            throw new UserNotfoundException("Reporter not found : " + id);
        }

        List<Issue> issues = issueRepository.findByAssignee(assignee);
        List<IssueDto> issueDtos = issues != null ? issues.stream().map(issue -> issueTrackerService.convertIssueToIssueDTO(issue)).collect(Collectors.toList()) : Collections.emptyList();

	    return issueDtos;
    }

    @GetMapping(value = "/reporter/{reporterId}")
	List<IssueDto> issuesForReporter(@PathVariable(value = "reporterId") Long id){

        User reporter = userRepository.findById(id);

        if (reporter == null) {
            throw new UserNotfoundException("Assignee not found : " + id);
        }

        List<Issue> issues = issueRepository.findByReporter(reporter);
        List<IssueDto> issueDtos = issues != null ? issues.stream().map(issue -> issueTrackerService.convertIssueToIssueDTO(issue)).collect(Collectors.toList()) : Collections.emptyList();

	    return issueDtos;
    }
    
    @GetMapping(value = "/")
    List<IssueDto> issuesBetweenDateCreated(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "fromDate") Date fromDate, 
                                         @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "toDate") Date toDate ) throws Exception{

        if (fromDate.before(toDate)){

            List<Issue> issues = issueRepository.findByCreatedBetween(fromDate, toDate);
            List<IssueDto> issueDtos = issues != null ? issues.stream().map(issue -> issueTrackerService.convertIssueToIssueDTO(issue)).collect(Collectors.toList()) : Collections.emptyList();

            return issueDtos;

        } else {
            throw new Exception("From date must be earlier than to date");
        }
    }

    @GetMapping(value = "/created/asc")
	List<IssueDto> issuesOrderByCreatedAsc(){

        List<Issue> issues = issueRepository.findAllByOrderByCreatedAsc();
        List<IssueDto> issueDtos = issues != null ? issues.stream().map(issue -> issueTrackerService.convertIssueToIssueDTO(issue)).collect(Collectors.toList()) : Collections.emptyList();

	    return issueDtos;
    }

    @GetMapping(value = "/created/desc")
	List<IssueDto> issuesOrderByCreatedDesc(){

        List<Issue> issues = issueRepository.findAllByOrderByCreatedDesc();
        List<IssueDto> issueDtos = issues != null ? issues.stream().map(issue -> issueTrackerService.convertIssueToIssueDTO(issue)).collect(Collectors.toList()) : Collections.emptyList();

	    return issueDtos;
    }
}
