package com.zhuozhi.dbrouter;

import java.lang.reflect.Method;

import javax.annotation.PostConstruct;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义注解注册到spring
 * @author ming
 *
 */
@Slf4j
@Configuration
public class DSConfiguration extends AbstractPointcutAdvisor {

	private static final long serialVersionUID = 4091225171686825015L;

	private Pointcut pointcut;

	private Advice advice;
	
	@PostConstruct
	public void init() {
		log.info("init LogAutoConfiguration start");
		this.pointcut = AnnotationMatchingPointcut.forMethodAnnotation(DS.class);
		this.advice = new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				try {
					Method method = invocation.getMethod();
					DS annotation = method.getAnnotation(DS.class);

					if (!DynamicDataSourceContextHolder.existDateSoure(annotation.value())) {
						log.error("Cannot find datasource {}", annotation.value());
						throw new Throwable("Cannot find datasource");
					}

					//根据DS注解的值设置数据源
					DynamicDataSourceContextHolder.setDateSoureName(annotation.value());

					//执行service的方法并返回
					return invocation.proceed();
				} finally {
					//清理数据源
					DynamicDataSourceContextHolder.clearDateSoureName();
				}
			}
		};
		log.info("init LogAutoConfiguration end");
	}

	@Override
	public Pointcut getPointcut() {
		return this.pointcut;
	}

	@Override
	public Advice getAdvice() {
		return this.advice;
	}

}
