package com.nando.dto;

public class TestDTO {

	private String testField1;

	public String getTestField1() {
		return testField1;
	}

	public void setTestField1(String testField1) {
		this.testField1 = testField1;
	}

	@Override
	public String toString() {
		return "TestDTO [testField1=" + testField1 + "]";
	}

}
