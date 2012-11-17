package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DtoContact extends BaseModelData {
	private static final long serialVersionUID = 6308342747740478807L;

	public static final String NAME = "name";
	public static final String PHONE = "phone";
	public static final String AGE = "age";
	public static final String IMAGE_SRC = "imageSrc";

	public DtoContact() { 	}
	
	public String getName() {
		return get(NAME);
	}

	public void setName(String name) {
		set(NAME, name);
	}

	public String getPhone() {
		return get(PHONE);
	}

	public void setPhone(String phone) {
		set(PHONE, phone);
	}

	public String getAge() {
		return get(AGE);
	}

	public void setAge(String age) {
		set(AGE, age);
	}

	public String getImageSrc() {
		return get(IMAGE_SRC);
	}

	public void setImageSrc(String imageSrc) {
		set(IMAGE_SRC, imageSrc);
	}

}
