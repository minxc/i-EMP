package com.minxc.core.entity;

/**
 * 员工类型
 */
public enum UserType {
    SYSTEM("99"),  //系统用户
    NORMAL("00"),  //正式用户
    OUTSOURCING("88"); //外协人员

    private final String code;

    UserType(String code){
        this.code = code;
    }

    public static UserType fromCode(String code) {
        if ( code.equals("99")) {
            return SYSTEM;
        }
        if ( code.equals("00")) {
            return NORMAL;
        }
        if ( code.equals("88")) {
        	return OUTSOURCING;
        }else {
        	 throw new UnsupportedOperationException(
                     "The code " + code + " is not supported!"
             );
        }
       
    }

    public String getCode() {
        return code;
    }
}
