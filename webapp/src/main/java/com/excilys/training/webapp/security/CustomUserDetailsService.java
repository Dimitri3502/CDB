package com.excilys.training.webapp.security;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.training.core.User;
import com.excilys.training.persistance.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user " + username);
		}
		return new CustomUserDetails(user);
	}

	private final static class CustomUserDetails implements UserDetails {
		
		
		private CustomUserDetails(User user) {
			this.user = user;
		}

		private User user;
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
//            return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+user.getRole())); 
        	return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
    				.collect(Collectors.toList());

        }

        @Override
        public String getUsername() {
            return user.getEmail();
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

        private static final long serialVersionUID = 5639683223516504866L;

		@Override
		public String getPassword() {
			return user.getPassword();
		}
    }
}
