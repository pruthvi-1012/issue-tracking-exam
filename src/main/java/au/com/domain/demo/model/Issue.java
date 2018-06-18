package au.com.domain.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.Date;

@Entity
@Table (name = "ISSUE", schema = "ISSUE_TRACKER")
public class Issue {
    @Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
    private String title;
    private String description;
	private String status;
	
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="reporter")
	private User reporter;
	
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="assignee")
	private User assignee;

	@Temporal(TemporalType.DATE)
	private Date created;
	
	@Temporal(TemporalType.DATE)
    private Date completed;
    
    public Issue() {
        super();
    }

    public Issue(Long id, String title, String description, String status, User reporter, User assignee, Date created, Date completed) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.reporter = reporter;
        this.assignee = assignee;
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

	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAsignee(User assignee) {
		this.assignee = assignee;
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
}