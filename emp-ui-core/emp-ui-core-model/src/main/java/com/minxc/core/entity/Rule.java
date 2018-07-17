package com.minxc.core.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="MR_RULES")
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
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "WRITE_DATE")
    private Date writeDate;
    @Column(name="CREATE_UID")
    private String createUid; //创建人
    @Column(name="UPDATE_UID")
    private String updateUid; //上次更新人



}
