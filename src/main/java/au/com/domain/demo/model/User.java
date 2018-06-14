package au.com.domain.demo.model;

public class User {
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

    @Override
    public String toString() {
        return String.format("Student [id=%s, name=%s]", id, userName);
    }
}