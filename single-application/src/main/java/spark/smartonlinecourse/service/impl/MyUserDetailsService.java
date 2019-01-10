package spark.smartonlinecourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spark.smartonlinecourse.dao.UserMapper;
import spark.smartonlinecourse.entity.MyUserDetails;
import spark.smartonlinecourse.entity.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @ClassName MyUserDetailsService
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/29 13:14
 * @Version 1.0
 **/
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.selectByEmail(s);
        if(user==null)
        {
            throw new  UsernameNotFoundException("找不到该用户");
        }
        return new MyUserDetails(user,getAuthorities("ROLE_USER"));
    }

    private Collection<GrantedAuthority> getAuthorities(String role) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }
}