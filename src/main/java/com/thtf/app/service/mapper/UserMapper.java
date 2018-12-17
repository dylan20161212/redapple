package com.thtf.app.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

import com.thtf.app.domain.Authority;
import com.thtf.app.domain.User;
import com.thtf.app.service.dto.UserDTO;

/**
 * Mapper for the entity User and its DTO called UserDTO.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as
 * MapStruct support is still in beta, and requires a manual step with an IDE.
 */
@Service
public class UserMapper implements EntityMapper<UserDTO, User> {

	public UserDTO userToUserDTO(User user) {
		return new UserDTO(user);
	}

	public List<UserDTO> usersToUserDTOs(List<User> users) {
		return users.stream().filter(Objects::nonNull).map(this::userToUserDTO).collect(Collectors.toList());
	}

	public User userDTOToUser(UserDTO userDTO) {
		if (userDTO == null) {
			return null;
		} else {
			User user = new User();
			user.setId(userDTO.getId());
			user.setLogin(userDTO.getLogin());
			user.setRealName(userDTO.getRealName());
			user.setEmail(userDTO.getEmail());
			user.setImageUrl(userDTO.getImageUrl());
			user.setActivated(userDTO.isActivated());
			user.setLangKey(userDTO.getLangKey());
			Set<Authority> authorities = this.authoritiesFromStrings(userDTO.getAuthorities());
			if (authorities != null) {
				user.setAuthorities(authorities);
			}
			user.setOrganizationName(userDTO.getOrganizationName());
			return user;
		}
	}

	public List<User> userDTOsToUsers(List<UserDTO> userDTOs) {
		return userDTOs.stream().filter(Objects::nonNull).map(this::userDTOToUser).collect(Collectors.toList());
	}

	public User userFromId(Long id) {
		if (id == null) {
			return null;
		}
		User user = new User();
		user.setId(id);
		return user;
	}

	public Set<Authority> authoritiesFromStrings(Set<String> strings) {
		return strings.stream().map(string -> {
			Authority auth = new Authority();
			auth.setName(string);
			return auth;
		}).collect(Collectors.toSet());
	}

	@Mapping(source = "organizationId", target = "organization")
	@Override
	public User toEntity(UserDTO userDTO) {
		if (userDTO == null) {
			return null;
		} else {
			User user = new User();
			user.setId(userDTO.getId());
			user.setLogin(userDTO.getLogin());
			user.setRealName(userDTO.getRealName());
			user.setEmail(userDTO.getEmail());
			user.setImageUrl(userDTO.getImageUrl());
			user.setActivated(userDTO.isActivated());
			user.setLangKey(userDTO.getLangKey());
			Set<Authority> authorities = this.authoritiesFromStrings(userDTO.getAuthorities());
			if (authorities != null) {
				user.setAuthorities(authorities);
			}
			user.setOrganizationName(userDTO.getOrganizationName());
			return user;
		}
	}

	@Mapping(source = "organization.id", target = "organizationId")
	@Mapping(source = "organization.orgName", target = "organizationOrgName")
	@Override
	public UserDTO toDto(User user) {
		return new UserDTO(user);
	}

	@Override
	public List<User> toEntity(List<UserDTO> dtoList) {
		return dtoList.stream().filter(Objects::nonNull).map(this::userDTOToUser).collect(Collectors.toList());
	}

	@Override
	public List<UserDTO> toDto(List<User> entityList) {
		return entityList.stream().filter(Objects::nonNull).map(this::userToUserDTO).collect(Collectors.toList());
	}
}
