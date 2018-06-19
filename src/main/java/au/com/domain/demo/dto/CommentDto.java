package au.com.domain.demo.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CommentDto {

    private Long id;
    private String description;
    private Long userId;
    private Long issueId;

    @Temporal(TemporalType.DATE)
    private Date created;
	
	public CommentDto() {
        super();
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public CommentDto(Long id, String description, Long userId, Long issueId, Date created) {
		super();
		this.id = id;
		this.description = description;
		this.setUserId(userId);
		this.setIssueId(issueId);
		this.created = created;
    }

	public Long getId() {
		return id;
    }
    
	public void setId(Long id) {
		this.id = id;
    }
    

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
}