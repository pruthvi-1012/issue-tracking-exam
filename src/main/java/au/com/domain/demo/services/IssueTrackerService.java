package au.com.domain.demo.services;

import org.springframework.stereotype.Component;

import au.com.domain.demo.dto.CommentDto;
import au.com.domain.demo.dto.IssueDto;
import au.com.domain.demo.model.Comment;
import au.com.domain.demo.model.Issue;

@Component
public interface IssueTrackerService {
    public IssueDto convertIssueToIssueDTO(Issue issue);
    public CommentDto convertCommentToCommentDTO(Comment comment);
}