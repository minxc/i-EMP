package com.minxc.core.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**********************************************************
 * 系统参数设置表
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/24
 *
 *********************************************************/
@Entity
@Table(name="MR_SYSTEM_PARAMETERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class SystemParameter implements Serializable {

    private static final long serialVersionUID = 6530416528271673220L;
    @Id
    private long  id;
    @Column(name="NAME")
    private String name;
    @Column(name="CODE")
    private String code;
    @Column(name="VALUE")
    private String value;
    @Column(name="NOTE")
    private String note;
    @Column(name="ACTIVE")
    private boolean active;

    @Column(name="CREATE_UID")
    private String createUid;
    @Column(name="WRITE_UID")
    private String writeUid;
    @Column(name="CREATE_DATE")
    private String createDate;
    @Column(name="WRITE_DATE")
    private String writeDate;
}
