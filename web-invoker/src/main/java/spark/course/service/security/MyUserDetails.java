package spark.course.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spark.course.entity.vo.UserVO;

import java.util.Collection;

/**
 * @ClassName MyUserDetails
 * @Description TODO
 * @Author Spark
 * @Date 3/4/2019 4:38 PM
 * @Version 1.0
 **/
public class MyUserDetails extends UserVO implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
