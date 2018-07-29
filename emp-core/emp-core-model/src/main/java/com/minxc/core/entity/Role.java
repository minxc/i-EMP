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
 * @date 2018/5/31
 *
 *********************************************************/
@Entity
@Table(name="MR_ROLE",indexes= {@Index(name="IDX_MR_ROLE_ROLE_CODE", columnList= "ROLE_CODE")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Role implements Serializable {

    private static final long serialVersionUID = 5322227963438907529L;

    @Id
    @Column(name="ROLE_ID", length = 64)
    private String id; //主键
    @Column(name="ROLE_NAME", length = 64)
    private String name; //组织机构名称
    @Column(name="ROLE_CODE", length = 64)
    private String code; //组织结构编码
    @Column(name="NOTE", length = 256)
    private String note; //组织结构备注
    
    @Builder.Default
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UserRoleLink> userRoleLinks = Sets.newHashSet();
    
    @Column(name="CREATE_UID", length = 64)
    private String createUid;
    @Column(name="WRITE_UID", length = 64)
    private String writeUid;
    @Column(name="CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name="WRITE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date writeDate;
}
