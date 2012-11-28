package br.com.m2msolutions.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DtoContact extends BaseModelData {
	private static final long serialVersionUID = 6308342747740478807L;

	public static final String NAME = "name";
	public static final String MATRICULA = "matricula";
	public static final String PHONE = "phone";
	public static final String AGE = "age";
	public static final String IMAGE_SRC = "imageSrc";

	public DtoContact(String name, String matricula, String phone, String age, String imageSrc) {
		setName(name);
		setMatricula(matricula);
		setPhone(phone);
		setAge(age);
		setImageSrc(imageSrc);
	}

	public DtoContact() {
	}

	public String getName() {
		return get(NAME);
	}

	public void setName(String name) {
		set(NAME, name);
	}

	public String getMatricula() {
		return get(MATRICULA);
	}

	public void setMatricula(String matricula) {
		set(MATRICULA, matricula);
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
