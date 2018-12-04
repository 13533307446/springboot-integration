package com.zhuozhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.zhuozhi.dao")
public class TablerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TablerApplication.class, args);
	}
}
