package com.ra.security.services;

import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.ra.familia.entities.GroupBean;
import com.ra.familia.entities.PersonBean;
import com.ra.familia.services.PersonGroupServiceImpl;
import com.ra.familia.services.PersonServiceImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private PersonServiceImpl userService = new PersonServiceImpl();
	private PersonGroupServiceImpl userGroupService = new PersonGroupServiceImpl();

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		PersonBean bean = new PersonBean();
		bean.setEmail(email);
		PersonBean person = userService.getItemByEmail(bean);
		
		SimpleGrantedAuthority authority = getGrantedAuthority(person.getGroupId());
		UserDetails userDetails = new User(person.getID(), person.getPassword(),
				Sets.newHashSet(authority));
		return userDetails;
	}

	private SimpleGrantedAuthority getGrantedAuthority(String groupId) {
		SimpleGrantedAuthority result = null;
		Optional<GroupBean> optional = userGroupService.getAllItems().stream().filter(p -> p.getID().equals(groupId)).findFirst();
		if (optional.isPresent()) {
			result = new SimpleGrantedAuthority("ROLE_"+optional.get().getName().toUpperCase());
		}
		return result;
	}
}
