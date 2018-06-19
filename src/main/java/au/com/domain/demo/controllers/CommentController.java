package au.com.domain.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import au.com.domain.demo.dto.CommentDto;
import au.com.domain.demo.model.Comment;
import au.com.domain.demo.repository.CommentRepository;
import au.com.domain.demo.repository.IssueRepository;
import au.com.domain.demo.repository.UserRepository;
import au.com.domain.demo.services.IssueTrackerService;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    IssueTrackerService issueTrackerService;

    @PostMapping("/")
    public CommentDto createComment(@Valid @RequestBody CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setDescription(commentDto.getDescription());
        comment.setUser(userRepository.findById(commentDto.getUserId()));
        comment.setIssue(issueRepository.findOne(commentDto.getIssueId()));  
        comment.setCreated(new Date());

        commentRepository.save(comment);
        
        return issueTrackerService.convertCommentToCommentDTO(comment);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable(value = "id") Long id) {
        Comment comment = commentRepository.findOne(id);
        commentRepository.delete(comment);
        return ResponseEntity.ok().build();
    }
}
