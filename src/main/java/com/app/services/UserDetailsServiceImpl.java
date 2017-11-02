package com.app.services;

/**
 * Created by Guillaume Gingembre on 02/11/2017.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.app.entities.*;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        User user = userService.findOne(userLogin);
        if (user == null) {
            throw new UsernameNotFoundException("User " + userLogin + "is not found");
        }
        return new UserDetailsExt(user);
    }

    private class UserDetailsExt implements UserDetails {
        private User user;
        private Collection<SimpleGrantedAuthority> grantedAuthorities;

        public  UserDetailsExt(User user) {
            this.user = user;
            this.grantedAuthorities = new ArrayList<>();//use Set if need
            Collection<Role> roles = user.getRoles();
            roles.forEach(role->grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return grantedAuthorities;
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
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

        public Collection<Role> getRoles() {
            return user.getRoles();
        }


    }
}
