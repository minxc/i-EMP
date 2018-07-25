package com.minxc.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**********************************************************
 * 员工信息
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/2
 *
 *********************************************************/
@Entity
@Table(name = "HR_EMPLOYEE")
@Getter
@Setter
public class Employee implements Serializable {

    private static final long serialVersionUID = 2409130177089282057L;

    @Id
    private String id;
    @Column(name="EMP_NAME")
    private String name;
    @Column(name="BIRTHDAY")
    private Date birthday;


    @Column(name = "CREATE_UID")
    private String createUid;
    @Column(name = "WRITE_UID")
    private String writeUid;
    @Column(name = "CREATE_DATE")
    private String createDate;
    @Column(name = "WRITE_DATE")
    private String writeDate;
}
