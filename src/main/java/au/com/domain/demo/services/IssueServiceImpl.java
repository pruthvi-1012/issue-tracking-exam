package au.com.domain.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.domain.demo.dto.IssueDto;
import au.com.domain.demo.model.Comment;
import au.com.domain.demo.model.Issue;
import au.com.domain.demo.repository.CommentRepository;
import au.com.domain.demo.repository.UserRepository;

@Service("issueService")
@Transactional
public class IssueServiceImpl implements IssueServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public IssueDto convertIssueToIssueDTO(Issue issue) {

        IssueDto issueDto = new IssueDto(issue.getId(), issue.getTitle(), issue.getDescription(), issue.getStatus(), issue.getCreated(), issue.getCompleted());
            issueDto.setReporter(issue.getReporter() != null ? userRepository.findOne(issue.getReporter().getId()) : null);
            issueDto.setAssignee(issue.getAssignee() != null ? userRepository.findOne(issue.getAssignee().getId()) : null);
 
            List<Comment> comments = commentRepository.findByIssue(issue);

            issueDto.setComments(comments != null ? comments : null);

        return issueDto;

    }

}