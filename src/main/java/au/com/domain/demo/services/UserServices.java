package au.com.domain.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import au.com.domain.demo.model.User;

@Repository
public class UserServices {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User findById(long id){
        return jdbcTemplate.queryForObject("select * from  ISSUE_TRACKER.USER where id=?", new Object[] {
            id
        },
        new BeanPropertyRowMapper < User > (User.class));
    }
}
