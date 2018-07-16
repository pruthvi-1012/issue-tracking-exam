package au.com.domain.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.domain.demo.dto.CommentDto;
import au.com.domain.demo.dto.IssueDto;
import au.com.domain.demo.model.Comment;
import au.com.domain.demo.model.Issue;
import au.com.domain.demo.repository.CommentRepository;
import au.com.domain.demo.repository.UserRepository;

@Service("issueTrackerService")
@Transactional
public class IssueTrackerServiceImpl implements IssueTrackerService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public IssueDto convertIssueToIssueDTO(Issue issue) {

        IssueDto issueDto = new IssueDto(issue.getId(), issue.getTitle(), issue.getDescription(), issue.getStatus());
            issueDto.setReporter(issue.getReporter() != null ? userRepository.findOne(issue.getReporter().getId()) : null);
            issueDto.setAssignee(issue.getAssignee() != null ? userRepository.findOne(issue.getAssignee().getId()) : null);
            issueDto.setCreated(issue.getCreated());
            issueDto.setCompleted(issue.getCreated() != null ? issue.getCompleted() : null);
            List<Comment> comments = commentRepository.findByIssue(issue);

            List<CommentDto> commentDtos = comments != null ? comments.stream().map(comment -> convertCommentToCommentDTO(comment)).collect(Collectors.toList()) : null ; 

            issueDto.setComments(commentDtos != null ? commentDtos : null);

        return issueDto;
    }


    @Override
    public CommentDto convertCommentToCommentDTO(Comment comment) {
        CommentDto commentDto = new CommentDto(comment.getId(), comment.getDescription(), comment.getUser().getId(), comment.getIssue().getId(), comment.getCreated());
        return commentDto;
    }


}