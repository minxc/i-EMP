package com.minxc.core.entity;

import com.google.common.collect.Sets;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**********************************************************
 * $()
 * @author Xcm   xianchangmin@126.com
 * @date 2018/5/31
 *
 *********************************************************/
@Entity
@Table(name="MR_ROLE")
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
    private String id; //主键
    @Column(name="NAME")
    private String name; //组织机构名称
    @Column(name="CODE")
    private String code; //组织结构编码
    @Column(name="NOTE")
    private String note; //组织结构备注
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UserRoleLink> user = Sets.newHashSet ( );

}
