package au.com.domain.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "USER", schema = "ISSUE_TRACKER")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    
    private String userName;
    
    public User() {
        super();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}