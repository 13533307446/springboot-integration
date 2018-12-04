package com.zhuozhi.controller;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuozhi.exception.BusinessException;
import com.zhuozhi.result.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * <b>GlobalExceptionHandler</b> is 自定义异常处理
 * </p>
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * 业务异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ BusinessException.class })
	@ResponseBody
	public Result<Object> handleBusinessException(BusinessException e) {
		log.warn("业务异常", e);
		Result<Object> result = new Result<Object>(e.getCode(), e.getMessage());
		return result;
	}
	
	/**
	 * @ModelAttribute 注解验证对象 出错所抛出的异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ BindException.class })
	@ResponseBody
	public Result<Object> handleBindException(BindException e) {
		log.warn("参数格式错误", e);
		ObjectError err = e.getAllErrors().get(0);
		Result<Object> result = new Result<Object>("Bxxx", err.getDefaultMessage());
		return result;
	}

	/**
	 * @RequestBody 注解验证对象 出错所抛出的异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseBody
	public Result<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.warn("参数格式错误", e);
		ObjectError err = e.getBindingResult().getAllErrors().get(0);
		Result<Object> result = new Result<Object>("Mxxx", err.getDefaultMessage());
		return result;
	}

	/**
	 * 系统异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ RuntimeException.class })
	@ResponseBody
	public Result<Object> handleRuntimeException(RuntimeException e) {
		log.error("系统内部异常", e);
		Result<Object> result = new Result<Object>("Rxxxx", "Exxxx");
		return result;
	}
}
