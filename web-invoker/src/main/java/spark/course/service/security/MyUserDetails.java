package spark.course.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spark.course.entity.bo.UserBO;

import java.util.Collection;

/**
 * @ClassName MyUserDetails
 * @Description TODO
 * @Author Spark
 * @Date 3/4/2019 4:38 PM
 * @Version 1.0
 **/
public class MyUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private UserBO userBO;
    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(){}

    public MyUserDetails(UserBO userBO, Collection<? extends GrantedAuthority> authorities){
        this.userBO=userBO;
        this.authorities=authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userBO.getEncryptPassword();
    }

    @Override
    public String getUsername() {
        return userBO.getEmail();
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
