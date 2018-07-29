package com.minxc.core.entity;

/**
 * @EnumName ActionType
 * @Description TODO
 * @Author Xianchang.min
 * @Date 2018/7/28 17:57
 * @Version 1.0
 **/

public enum ActionType {

    NEW("NEW"),
    DELETE("DELETE"),
    UPDATE("UPDATE"),
    QUERY("QUERY");

    private final String code;

    ActionType(String code) {
        this.code = code;
    }


    public static ActionType fromCode(String code) {

        switch (code) {
            case "NEW":
                return NEW;
            case "DELETE":
                return DELETE;
            case "UPDATE":
                return UPDATE;
            case "QUERY":
                return QUERY;
            default: {
                throw new UnsupportedOperationException("The code " + code + " is not supported!");
            }

        }
    }

    public String getCode() {
        return code;
    }
}