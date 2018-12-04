package com.zhuozhi.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zhuozhi.entity.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<User> {
	@Select("SELECT * FROM USERS WHERE USERNAME = #{username}")
	User myBatisFindByName(@Param("username") String username);
}
