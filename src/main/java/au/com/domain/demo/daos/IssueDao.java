package au.com.domain.demo.daos;

import java.util.Date;
import java.util.Set;

import antlr.collections.List;
import au.com.domain.demo.model.Comment;
import au.com.domain.demo.model.User;

public class IssueDao {

    private Long id;
    private String title;
    private String description;
    private String status;
    private User assignee;
    private User reporter;
    private Date created;
    private Date completed;
	private Set<Comment> comments;
	
	public IssueDao() {
        super();
	}
	
	public IssueDao(Long id, String title, String description, String status, Date created, Date completed) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.created = created;
		this.completed = completed;
    }

	public Long getId() {
		return id;
    }
    
	public void setId(Long id) {
		this.id = id;
    }
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCompleted() {
		return completed;
	}

	public void setCompleted(Date completed) {
		this.completed = completed;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
}