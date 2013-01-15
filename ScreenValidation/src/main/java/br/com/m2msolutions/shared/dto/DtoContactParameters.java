package br.com.m2msolutions.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DtoContactParameters implements IsSerializable{
	
	private String code;
	private String name;
	private Long postId;
	private Long functionId;
	
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public Long getPostId() {
		return postId;
	}
	public Long getFunctionId() {
		return functionId;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}
	
	
}
