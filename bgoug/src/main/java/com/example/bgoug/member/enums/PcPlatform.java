package com.example.bgoug.member.enums;

public enum PcPlatform {

    XP("001"),
    VISTA("002"),
    LINUX("003"),
    UNIX("004"),
    WINDOWS8("005");

    private String key;

    PcPlatform(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
