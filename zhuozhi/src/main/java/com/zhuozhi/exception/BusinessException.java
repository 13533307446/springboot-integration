package com.zhuozhi.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 8862339523905913528L;

	public BusinessException(String message) {
		super(message);
		this.message = message;
	}

	public BusinessException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	/**
	 * 错误代码
	 */
	private String code;

	/**
	 * 错误信息
	 */
	private String message;

	/**
	 * 获取 错误代码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置 错误代码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取 错误信息
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置 错误信息
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
