package com.zhuozhi.dbrouter;

import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

@Configuration
//注意需要优先于DruidDataSourceAutoConfigure 和 DataSourceAutoConfiguration，不然会bean冲突
@AutoConfigureBefore(DruidDataSourceAutoConfigure.class)
public class DataSourceConfiguration {

	@Autowired
	private Environment environment;

	/**
	 * 配置多数据源
	 * @return
	 * @throws SQLException 
	 */
	@Bean(name = "dataSource")
	@Primary
	public DynamicDataSource dataSource() throws SQLException {
		Binder binder = Binder.get(environment);
		DruidDataSource master = binder.bind("spring.datasource.master", Bindable.of(DruidDataSource.class)).get();
		DruidDataSource slave = binder.bind("spring.datasource.slave", Bindable.of(DruidDataSource.class)).get();

		//预加载
		master.init();
		slave.init();

		LinkedHashMap<Object, Object> targetDatasources = new LinkedHashMap<>();
		targetDatasources.put(master.getName(), master);
		targetDatasources.put(slave.getName(), slave);

		DynamicDataSourceContextHolder.datasourceNameSet.add(master.getName());
		DynamicDataSourceContextHolder.datasourceNameSet.add(slave.getName());

		//设置默认使用数据源为slave库
		return new DynamicDataSource(slave, targetDatasources);
	}

}
