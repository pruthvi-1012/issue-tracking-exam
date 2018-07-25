package au.com.domain.demo.services;

import org.springframework.stereotype.Component;

import au.com.domain.demo.dto.CommentDto;
import au.com.domain.demo.dto.IssueDto;
import au.com.domain.demo.model.Comment;
import au.com.domain.demo.model.Issue;
import au.com.domain.demo.model.User;

@Component
public interface IssueTrackerService {
    public User findUserById(long userId);
    public Issue findIssueById(long issueId)
    public IssueDto convertIssueToIssueDTO(Issue issue);
    public CommentDto convertCommentToCommentDTO(Comment comment);
}