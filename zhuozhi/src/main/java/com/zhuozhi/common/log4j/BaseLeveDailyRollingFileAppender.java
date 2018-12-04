package com.zhuozhi.common.log4j;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * 名称：BaseLeveDailyRollingFileAppender <br>
 * 描述：自定义日志文件类，使日志记录的级别等于定义的级别，不进行继承关系<br>
 *
 */
public class BaseLeveDailyRollingFileAppender extends DailyRollingFileAppender {
    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        //只判断是否相等，而不判断优先级
        return this.getThreshold().equals(priority);
    }
}
