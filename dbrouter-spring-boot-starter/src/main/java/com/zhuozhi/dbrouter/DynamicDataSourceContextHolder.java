package com.zhuozhi.dbrouter;

import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamicDataSourceContextHolder {

	/*
	 * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
	 * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
	 */
	private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();

	/*
	 * 管理所有的数据源id,用于数据源的判断
	 */
	public static Set<String> datasourceNameSet = new HashSet<String>();

	/**
	 * @Title: setDateSoureName
	 * @Description: 设置数据源的变量
	 * @param dateSoureName
	 * @return void
	 * @throws
	 */
	public static void setDateSoureName(String dateSoureName) {
		log.info("setDateSoureName {} {}", Thread.currentThread().getName(), dateSoureName);
		CONTEXT_HOLDER.set(dateSoureName);
	}

	/**
	 * @Title: getDateSoureName
	 * @Description: 获得数据源的变量
	 * @return String
	 * @throws
	 */
	public static String getDateSoureName() {
		log.info("getDateSoureName {} {}", Thread.currentThread().getName(), CONTEXT_HOLDER.get());
		return CONTEXT_HOLDER.get();
	}

	/**
	 * @Title: clearDateSoureNameList
	 * @Description: 清空所有的数据源变量
	 * @return void
	 * @throws
	 */
	public static void clearDateSoureName() {
		log.info("clearDateSoureName");
		CONTEXT_HOLDER.remove();
	}

	/**
	 * @Title: existDateSoure
	 * @Description: 判断数据源是否已存在
	 * @param dateSoureName
	 * @return boolean
	 * @throws
	 */
	public static boolean existDateSoure(String dateSoureName) {
		return datasourceNameSet.contains(dateSoureName);
	}

}
