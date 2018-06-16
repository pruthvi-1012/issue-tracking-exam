package au.com.domain.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Table (name = "ISSUE", schema = "ISSUE_TRACKER")
public class Issue {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String status;
    private Long reporter;
    private Long asignee;
    private Date created;
    private Date completed;
    
    public Issue() {
        super();
    }

    public Issue(Long id, String title, String description, String status, Long reporter, Long asignee, Date created, Date completed) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.reporter = reporter;
        this.asignee = asignee;
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

	public Long getReporter() {
		return reporter;
	}

	public void setReporter(Long reporter) {
		this.reporter = reporter;
	}

	public Long getAsignee() {
		return asignee;
	}

	public void setAsignee(Long asignee) {
		this.asignee = asignee;
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