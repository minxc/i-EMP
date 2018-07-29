package com.minxc.core.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**********************************************************
 * 数据权限访问类
 * @author Xcm   xianchangmin@126.com
 * @date 2018/5/31
 *
 *********************************************************/
@Entity
@Table(name="MR_RULE", indexes = {@Index(name = "IDX_MR_PERMISSION_VIEWID", columnList = "VIEW_ID"),@Index(name = "IDX_MR_PERMISSION_ACTIONID", columnList = "ACTION_ID")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Rule implements Serializable {

    private static final long serialVersionUID = 9119401136982487743L;

    @Id
    private String id; //主键
    @Column(name="CONTENTS")
    private String contents;
    
    @Column(name = "VIEW_ID", length=64)
    private String view;
    @Column(name = "ACTION_ID", length=64)
    private String action;
    @Column(name = "ACTIVE") 
    private boolean active;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "WRITE_DATE")
    private Date writeDate;
    @Column(name="CREATE_UID")
    private String createUid; //创建人
    @Column(name="WRITE_UID")
    private String writeUid; //上次更新人



}
