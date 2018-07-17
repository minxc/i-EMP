package com.minxc.core.entity;

import com.google.common.collect.Sets;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**********************************************************
 * $()
 * @author Xcm   xianchangmin@126.com
 * @date 2018/5/30
 *
 *********************************************************/

@Entity
@Table(name="MR_USERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class User implements Serializable {

    private static final long serialVersionUID = 4038805690758516090L;
    @Id
    @Column(name="ID")
    private String id;
    @Column(name="USER_NAME")
    private String userName;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="LANG")
    private String lang; // 语言
    @Column(name="ACTIVE")
    private boolean active;
    @Column(name="USER_TYPE")
    private String userType; // 用户类型 区分内部用户还是外部用户，亦或是系统操作级的用户
    @Column(name="ALLOW_COUNT")
    private int  allowCount; //允许在线数量
    @Column(name="IP")
    private String  ip;   //登录常用地址
    @Column(name = "IP_BINDABLE")
    private boolean ipBindable; //是否登录地址绑定
    @Column(name = "ORG_ID")
    private Organization orgOid;

    @Column(name = "CREATE_AT")
    private Date createDate;
    @Column(name = "UPDATE_AT")
    private Date updateDate;
    @Column(name="CREATE_UID")
    private String createUid; //创建人
    @Column(name="UPDATE_UID")
    private String updateUid; //上次更新人
    @Column(name="IS_EMPLOYEE")
    private boolean isEmployee;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UserRoleLink> roles = Sets.newHashSet();
}
