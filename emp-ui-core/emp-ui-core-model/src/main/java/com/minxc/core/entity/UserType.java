package com.minxc.core.entity;

/**
 * 员工类型
 */
public enum UserType {
    SYSTEM("99"),  //系统用户
    NORMAL("00"),  //正式用户
    OUTSOURCING("99"); //外协人员

    private final String code;

    UserType(String code){
        this.code = code;
    }

    public static UserType fromCode(String code) {
        if ( code == 'M' || code == 'm' ) {
            return MALE;
        }
        if ( code == 'F' || code == 'f' ) {
            return FEMALE;
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!"
        );
    }

    public String getCode() {
        return code;
    }
}
