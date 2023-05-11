package com.example.java_demo_test.constants;

public enum RtnCode {
     //UTF8 refer 改!!!!
	//200是什麼??Http狀態碼吧
	SUCCESSFUL("200","successful!!!"),//表示多個
	CANNOT_EMPTY("400","Account or password cannot be empty!!!"),
	CANNOT_ALLEMPTY("400","City cannot be empty!!!"),
	DATA_ERROR("400","Account or password or name is error!!!"),
	NOT_FOUND("404","Not found!!!"),
	ALREADY_EXISTS("400","Account or password or name is exist");
	
	private String code;
	
	private String message;
	
	//無空的建構方法why

	private RtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {//why 只需要get 不用set?因為在這裡設好了
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
