package com.minxc.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**********************************************************
 * $()
 * @author Xcm   xianchangmin@126.com
 * @date 2018/5/31
 *
 *********************************************************/
@Entity
@Table(name="MR_ORGANIZATION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Organization implements Serializable {

    private static final long serialVersionUID = 2246665191774203262L;
    @Id
    private String id; //主键
    private String name; //组织机构名称
    private String code; //组织结构编码
    private int level; //组织结构层级
    private String path; //组织结构路径
    private String note; //组织结构备注

    /**
     * 父组织
     */
    @ManyToOne
    @JoinColumn(name = "PARENT_OID")
    private Organization parentOid;

    /**子组织*/
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_OID")
    private Set<Organization> children = new HashSet<Organization> ();


    @Column(name = "SEQ")
    private int sequence; //顺序
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "write_date")
    private Date writeDate;
    @Column(name="CREATE_UID")
    private String createUid; //创建人
    @Column(name="UPDATE_UID")
    private String updateUid; //上次更新人


}
