package au.com.domain.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import au.com.domain.demo.model.Issue;

@Repository
public class IssueServices {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Issue findById(long id){
        return jdbcTemplate.queryForObject("select * from  ISSUE_TRACKER.USER where id=?", new Object[] {
            id
        },
        new BeanPropertyRowMapper < Issue > (Issue.class));
    }

    public Issue insertUser(Issue issue){
        return jdbcTemplate.queryForObject("insert into ISSUE_TRACKER.USER (USERNAME) " + "values (?)", new Object[] {
            issue.getTitle()
        },
        new BeanPropertyRowMapper < Issue > (Issue.class));
    }
}