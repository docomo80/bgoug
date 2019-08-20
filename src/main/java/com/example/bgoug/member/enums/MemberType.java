package com.example.bgoug.member.enums;

public enum MemberType {
	
    INDIVIDUAL("INDIVIDUAL"),
    CORPORATE("CORPORATE");
	
	 private String key; 
	 
	MemberType(String key) {
	        this.key = key;
	    }

	    public String getKey() {
	        return key;
	    }
}
