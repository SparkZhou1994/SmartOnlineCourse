package spark.course.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import spark.course.api.FeignUserApi;
import spark.course.entity.bo.UserBO;
import spark.course.util.JsonUtil;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @ClassName MyUserDetailsService
 * @Description TODO
 * @Author Spark
 * @Date 3/4/2019 4:47 PM
 * @Version 1.0
 **/
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    FeignUserApi userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
         UserBO userBO = JsonUtil.json2Bean(userService.selectByEmail(s), UserBO.class);
        if(userBO == null)
        {
            throw new  UsernameNotFoundException("找不到该用户");
        }
        return new MyUserDetails(userBO,getAuthorities("ROLE_USER"));
    }

    private Collection<GrantedAuthority> getAuthorities(String role) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }
}
