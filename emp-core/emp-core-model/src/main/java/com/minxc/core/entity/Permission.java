package com.minxc.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**********************************************************
 * 权限表，涉及ROLE,Action, Rule关系
 * @author Xcm   xianchangmin@126.com
 * @date 2018/5/31
 *
 *********************************************************/
@Entity
@Table(name = "MR_PERMISSION", indexes = {@Index(name = "IDX_MR_PERMISSION_CODE", columnList = "CODE")})
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Permission implements Serializable {

    private static final long serialVersionUID = -3544380775368936034L;

    @Id
    private String id; //主键
    @Column(name = "VIEW_ID", length=64)
    private String view;   //所属视图
    @Column(name = "ACTION_ID", length=64)
    private String action; //操作
    @Column(name = "RULE_ID", length=64) 
    private String  rule;  //对应的数据权限
    @Column(name = "PERM_TYPE", length=16) 
    private String type;    // 权限的类型  00：功能权限  01：操作权限 02：数据权限
    @Column(name = "ACTIVE") 
    private boolean active;   //是否有效
    @Column(name = "CREATE_UID")
    private String createUid;
    @Column(name = "WRITE_UID")
    private String writeUid;
    @Column(name = "CREATE_DATE")
    private String createDate;
    @Column(name = "WRITE_DATE")
    private String writeDate;

}
