package com.qurich.common.model;

/**
 * 统一返回类型
 * @author JD
 *
 * @param <T>
 */
public class Result<T> {

	private ResultCodeEnum code;
    private String msg;
    private  T data;

    public ResultCodeEnum getCode() {
        return code;
    }

    public Result() {
    }

    public Result setCode(ResultCodeEnum resultCode) {
        this.code = resultCode;
        return this;
    }


    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }
}
