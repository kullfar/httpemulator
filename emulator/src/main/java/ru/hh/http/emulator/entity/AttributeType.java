package ru.hh.http.emulator.entity;

public enum AttributeType {

	PATH(0),
	
	PARAMETER(1),
	
	HEADER(2),
	
	BODY(3),
	
	METHOD(4),
	
	STATUS(5),
	
	PROTOCOL(6);
	
	private final int type;

	private AttributeType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	
	
}