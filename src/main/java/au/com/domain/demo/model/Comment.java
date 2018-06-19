package au.com.domain.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.Date;

@Entity
@Table (name = "COMMENT", schema = "ISSUE_TRACKER")
public class Comment {
    @Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="user")
	private User user;
	
	@JsonIgnore
	@ManyToOne(targetEntity=Issue.class)
	@JoinColumn(name="issue")
	private Issue issue;

	private String description;

	@Temporal(TemporalType.DATE)
	private Date created;
	
    
    public Comment() {
        super();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}