package com.zhuozhi.dbrouter;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager {

	private static final long serialVersionUID = 1715868041601265926L;

	public DynamicDataSourceTransactionManager(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 重写doBegin，切换数据源
	 * 若有 @Transaction 注解，事务只读，设置为从；否则主库
	 */
	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		if (definition.isReadOnly()) {
			DynamicDataSourceContextHolder.setDateSoureName("slave");
		} else {
			DynamicDataSourceContextHolder.setDateSoureName("master");
		}
		super.doBegin(transaction, definition);
	}

	/**
	 * 结束事务清理刚刚设置的数据源
	 */
	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		DynamicDataSourceContextHolder.clearDateSoureName();
		super.doCleanupAfterCompletion(transaction);
	}

}
