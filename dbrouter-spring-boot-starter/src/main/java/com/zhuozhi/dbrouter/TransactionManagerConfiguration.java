package com.zhuozhi.dbrouter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class TransactionManagerConfiguration implements TransactionManagementConfigurer {

	@Autowired
	private DynamicDataSource dataSource;

	@Override
	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DynamicDataSourceTransactionManager(dataSource);
	}
}
