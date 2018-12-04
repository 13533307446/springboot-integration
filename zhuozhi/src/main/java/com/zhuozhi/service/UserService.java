package com.zhuozhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhuozhi.dao.UserDao;
import com.zhuozhi.dbrouter.DS;
import com.zhuozhi.entity.User;

@Service
public class UserService {
	@Autowired
	UserDao userDao;

	public User getUserByName(String username) {
		return userDao.myBatisFindByName(username);
	}

	public User getUserById(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Transactional
	public User getUserByIdInT(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}

	@DS(value = "mastersss")
	public User getUserByIdDS1(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}

	@DS(value = "slavesss")
	public User getUserByIdDS2(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Transactional
	public User saveUser(String userName, String mobile) {
		User user = new User();
		user.setUserName(userName);
		user.setMobile(mobile);
		userDao.insert(user);
		return user;
	}

}
