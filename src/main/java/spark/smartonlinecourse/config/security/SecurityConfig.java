package spark.smartonlinecourse.config.security;

/**
 * @ClassName SecurityConfig
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/19 16:41
 * @Version 1.0
 **/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import spark.smartonlinecourse.service.impl.MyUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService myUserDetailsService(){
        return new MyUserDetailsService();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/my_index").access("hasRole('ROLE_USER')")
                .and()
                .formLogin().loginPage("/login_and_register").failureForwardUrl("/login_and_register?error").successForwardUrl("/my_index");
    }
}
