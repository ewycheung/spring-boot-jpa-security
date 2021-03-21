package com.example.demo.security;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
        ApplicationUserPermission.COURSE_READ,
        ApplicationUserPermission.COURSE_WRITE,
        ApplicationUserPermission.STUDENT_READ,
        ApplicationUserPermission.STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(
            ApplicationUserPermission.COURSE_READ,            
            ApplicationUserPermission.STUDENT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return this.permissions;
    }
    
    public Set<GrantedAuthority> getGrantedAuthority() {
        Set<GrantedAuthority> permissions = getPermissions().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }

    public static ApplicationUserRole getApplicationUserRole(String role) {
        for (ApplicationUserRole userRole: ApplicationUserRole.values()) {
            if (userRole.name().equals(role)) {
                return userRole;
            }
        }

        return null;
    }
}
