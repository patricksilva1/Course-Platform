package dev.patricksilva.course_platform.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.patricksilva.course_platform.dao.RoleDao;
import dev.patricksilva.course_platform.dao.UserDao;
import dev.patricksilva.course_platform.entity.Role;
import dev.patricksilva.course_platform.entity.User;
import dev.patricksilva.course_platform.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService{

    private UserDao userDao;

    private RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public User loadUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User createUser(String email, String password) {
        return userDao.save(new User(email, password));
    }

    @Override
    public void assignRoleToUser(String email, String roleName) {
        User user = loadUserByEmail(email);
        Role role = roleDao.findByName(roleName);
        user.assignRoleToUser(role);
    }
	
}
