package com.example.java_demo_test.constants;

public enum RtnCode {
     //UTF8 refer ��!!!!
	//200�O����??Http���A�X�a
	SUCCESSFUL("200","successful!!!"),//��ܦh��
	CANNOT_EMPTY("400","Account or password cannot be empty!!!"),
	CANNOT_ALLEMPTY("400","City cannot be empty!!!"),
	DATA_ERROR("400","Account or password or name is error!!!"),
	NOT_FOUND("404","Not found!!!"),
	ALREADY_EXISTS("400","Account or password or name is exist");
	
	private String code;
	
	private String message;
	
	//�L�Ū��غc��kwhy

	private RtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {//why �u�ݭnget ����set?�]���b�o�̳]�n�F
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
