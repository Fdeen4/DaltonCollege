package com.example.demo.services;

import com.example.demo.models.AppRole;
import com.example.demo.models.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUDS implements UserDetailsService {
    private AppUserRepository users;

    public SSUDS(AppUserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            AppUser user = users.findByuserName(username);
            if (user == null)
                throw new UsernameNotFoundException("Invalid username or password");
            else {
                System.out.println("picked " + user.getUserName() + "from the database");
                return new User(user.getUserName(), user.getPassWord(), getAuthorities(user));
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    private Set<GrantedAuthority> getAuthorities(AppUser user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (AppRole role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(grantedAuthority);
            System.out.println("User authority: " + role.getRoleName());
        }

        return authorities;
    }
}