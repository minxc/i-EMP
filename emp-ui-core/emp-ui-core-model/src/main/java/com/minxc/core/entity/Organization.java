package com.minxc.core.entity;

import lombok.*;

import javax.persistence.*;

import com.google.common.collect.Sets;

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
@Table(name = "MR_ORGANIZATION", indexes = {@Index(name="IDX_MR_ORG_ORG_CODE", columnList = "ORG_CODE"),
    @Index(name="IDX_MR_ORG_PARENT_OID", columnList = "PARENT_OID")})
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
    @Column(name = "ORG_ID", length = 64)
    private String id; //主键
    @Column(name = "ORG_NAME", length = 64)
    private String name; //组织机构名称
    @Column(name = "ORG_CODE", length = 64)
    private String code; //组织结构编码
    @Column(name = "ORG_LEVEL")
    private int level; //组织结构层级
    @Column(name = "ORG_PATH", length = 255)
    private String path; //组织结构路径

    /**
     * 父组织
     */
    @ManyToOne
    @JoinColumn(name = "PARENT_OID")
    private Organization parent;

    /**
     * 子组织
     */
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_OID",foreignKey = @ForeignKey(name="FK_ORG_PARENT_ID"))
    private Set<Organization> children = new HashSet<Organization>();

    @Column(name = "ORG_SEQ")
    private int sequence; //顺序

    @Column(name = "ORG_ACTIVE")
    private boolean active;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "WRITE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date writeDate;

    @Column(name = "CREATE_UID", length = 64)
    private String createUid; //创建人

    @Column(name = "WRITE_UID", length = 64)
    private String writeUid; //上次更新人

    @Column(name = "ORG_NOTES", length = 255)
    private String note; //组织结构备注
    
    @Builder.Default
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<UserOrganizationLink> userOrganizationLinks = Sets.newHashSet();

}
