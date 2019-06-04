package com.nmhung.enums;

enum TestEnum {
	HDZ("data"), TEST("test");

	private final String value;

	private TestEnum(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name();
	}
	public String getValue() {
		return value;
	}
	public static void main(String[] args) {
		System.out.println(TestEnum.HDZ);
		for (TestEnum item : TestEnum.values()) {
			System.out.println(item.name() + "\t" + item.getValue());
		}
	}

}
