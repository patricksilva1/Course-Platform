package dev.patricksilva.course_platform.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.patricksilva.course_platform.dao.RoleDao;
import dev.patricksilva.course_platform.entity.Role;
import dev.patricksilva.course_platform.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao;

	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public Role createRole(String roleName) {
		return roleDao.save(new Role(roleName));
	}
}