package au.com.domain.demo.services;

import org.springframework.stereotype.Component;

import au.com.domain.demo.dto.IssueDto;
import au.com.domain.demo.model.Issue;

@Component
public interface IssueServices {
    public IssueDto convertIssueToIssueDTO(Issue issue);
}