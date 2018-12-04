package com.zhuozhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuozhi.entity.User;
import com.zhuozhi.exception.BusinessException;
import com.zhuozhi.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("/user/getUserByName")
	@ResponseBody
	public User getUserByName(String username) {
		log.debug("参数username:{}", username);
		return userService.getUserByName(username);
	}

	@RequestMapping("/user/getUserById")
	@ResponseBody
	public User getUserById(Integer id) {
		log.debug("参数id:{}", id);
		return userService.getUserById(id);
	}

	@RequestMapping("/user/getUserByIdInT")
	@ResponseBody
	public User getUserByIdInT(Integer id) {
		log.debug("参数id:{}", id);
		return userService.getUserByIdInT(id);
	}

	@RequestMapping("/user/getUserByIdDS1")
	@ResponseBody
	public User getUserByIdDS1(Integer id) {
		log.debug("参数id:{}", id);
		return userService.getUserByIdDS1(id);
	}

	@RequestMapping("/user/getUserByIdDS2")
	@ResponseBody
	public User getUserByIdDS2(Integer id) {
		log.debug("参数id:{}", id);
		return userService.getUserByIdDS2(id);
	}

	@RequestMapping("/user/info")
	public String index(Model model, String username) {
		model.addAttribute("user", userService.getUserByName(username));
		model.addAttribute("test", "ass");
		return "/user/info";
	}

	@RequestMapping("/user/testRuntimeException")
	public String testRuntimeException() {
		int i = 1 / 0;
		return "/user/info";
	}

	@RequestMapping("/user/testBusinessException")
	public String testBusinessException() {
		if (true) {
			throw new BusinessException("U001", "U001错误");
		}
		return "/user/info";
	}

	@RequestMapping("/user/save")
	@ResponseBody
	public User save(String userName, String mobile) {
		return userService.saveUser(userName, mobile);
	}
}
