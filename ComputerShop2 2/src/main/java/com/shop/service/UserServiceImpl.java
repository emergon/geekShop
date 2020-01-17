package com.shop.service;

import com.shop.dao.UserDao;
import com.shop.entities.Role;
import com.shop.entities.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao udao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    

    public User findByUsername(String username) {
        return udao.findByUsername(username);
    }


    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        udao.save(user);
    }


    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        
        User user = udao.findByUsername(string);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username");
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
        
        return userDetails;
    }
    
    private List<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
        List<GrantedAuthority> authorities = new ArrayList();
        for(Role r:roles){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(r.getRname());
            authorities.add(authority);
        }
        return authorities;
    }
    
}
