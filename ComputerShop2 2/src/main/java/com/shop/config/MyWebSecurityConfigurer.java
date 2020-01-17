package com.shop.config;

import com.shop.service.UserService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Configuration
@EnableWebSecurity
public class MyWebSecurityConfigurer extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource datasource;
    @Autowired
    private UserService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //UserBuilder userBuilder = User.builder();
        //auth.jdbcAuthentication().dataSource(datasource);
        auth.authenticationProvider(authenticationProvider());
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder.username("admin").password("{noop}1234").roles("ADMIN", "USER"))
//                .withUser(userBuilder.username("user").password("{noop}1234").roles("USER"))
//                .withUser(userBuilder.username("nick").password("{noop}1234").roles("GUEST"));
                
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//Restrict access based on HttServletRequest
//             .anyRequest().authenticated()//Any request to the app must be authenticated(logged in)
//             .antMatchers("/").hasAnyRole("USER","ADMIN")
//             .antMatchers("/admin/**").hasRole("ADMIN")
//             .antMatchers("/*/create/**","/*/update/**", "/*/delete/**").hasRole("ADMIN")
             .and()
             .formLogin()//We are customizing the form login process
             .loginPage("/loginPage")//Show my form at the request mapping
             .loginProcessingUrl("/authenticate")//Login form will POST data to this URL for processing username and password
             .permitAll()//Allow everyone to see Login page. Don't have to be logged in.
             .and().logout().permitAll()
             .and().exceptionHandling().accessDeniedPage("/access-denied");
    }
    
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}
