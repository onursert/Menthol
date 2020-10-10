package org.onursert.obss.menthol.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class HomeService {

    public String home() {
        if (hasRole("ROLE_ADMIN")) {
            return "redirect:/admin/index";
        }
        return "redirect:/user/index";
    }

    public String homeIndex() {
        if (hasRole("ROLE_ADMIN")) {
            return "redirect:/admin/index";
        }
        return "redirect:/user/index";
    }

    private boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }
}
