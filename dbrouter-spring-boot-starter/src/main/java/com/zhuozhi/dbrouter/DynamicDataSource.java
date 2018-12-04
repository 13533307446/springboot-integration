package com.zhuozhi.dbrouter;

import java.util.LinkedHashMap;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
	/**
	 * 默认数据源
	 */
	private DataSource defaultTargetDataSource;

	/**
	 * 数据源列表
	 */
	private LinkedHashMap<Object, Object> targetDataSources;

	public DynamicDataSource(DataSource defaultTargetDataSource, LinkedHashMap<Object, Object> targetDataSources) {
		this.defaultTargetDataSource = defaultTargetDataSource;
		this.targetDataSources = targetDataSources;
	}

	@Override
	protected Object determineCurrentLookupKey() {
		String dataSource = DynamicDataSourceContextHolder.getDateSoureName();
		log.info("DynamicDataSource current db name: {}", dataSource);
		return dataSource;
	}

	@Override
	public void afterPropertiesSet() {
		super.setDefaultTargetDataSource(defaultTargetDataSource);
		super.setTargetDataSources(targetDataSources);
		super.afterPropertiesSet();
	}

}
