package spark.course.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spark.course.api.FeignUserApi;
import spark.course.entity.bo.UserBO;
import spark.course.error.BusinessException;
import spark.course.util.JsonUtil;

import java.util.ArrayList;
import java.util.Collection;


/**
 * @ClassName MyUserDetailsService
 * @Description TODO
 * @Author Spark
 * @Date 2/5/2019 7:54 PM
 * @Version 1.0
 **/
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    FeignUserApi userService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBO userBO = JsonUtil.json2Bean(userService.selectByEmail(username), UserBO.class);
        if(userBO == null)
        {
            throw new UsernameNotFoundException("找不到该用户");
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
