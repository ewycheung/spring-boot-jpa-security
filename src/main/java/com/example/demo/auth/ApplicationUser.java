package com.example.demo.auth;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import com.example.demo.security.User;
import com.google.common.collect.Sets;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ApplicationUser implements UserDetails {        
    private static final long serialVersionUID = -1536218992076984911L;
    
    private final Long id;
    private final String username;
    private final String password;
    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    public ApplicationUser(Long id,
                            String username, 
                            String password,
                            Set<? extends GrantedAuthority> grantedAuthorities,
                            boolean isAccountNonExpired,
                            boolean isAccountNonLocked,
                            boolean isCredentialsNonExpired,
                            boolean isEnabled) {                
        this.id = id;                                
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public static ApplicationUser build(User user) {        
		Set<GrantedAuthority> authorities = Sets.newHashSet();
        user.getRoles().forEach(role -> {
            authorities.addAll(role.getName().getGrantedAuthority());            
        });

		return new ApplicationUser(
                user.getId(),
				user.getUsername(), 				
				user.getPassword(), 
				authorities,
                true,
                true,
                true,
                true);
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }
    
    public Long getId() {
        return this.id;
    }
    
    @Override
    public String getPassword() {        
        return this.password;
    }

    @Override
    public String getUsername() {        
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {        
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {        
        return this.isEnabled;
    }
    
    @Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
            ApplicationUser user = (ApplicationUser) o;
	    return Objects.equals(id, user.id);
	}
}
