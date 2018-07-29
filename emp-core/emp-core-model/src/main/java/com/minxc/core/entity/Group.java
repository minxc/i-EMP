package com.minxc.core.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**********************************************************
 * $()
 * @author Xcm   xianchangmin@126.com
 * @date 2018/5/31
 *
 *********************************************************/
@Entity(name = "MR_GROUPS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Group implements Serializable {
    private static final long serialVersionUID = 9153837232212503655L;
    @Id
    @Column(name = "GROUP_ID")
    private String id; //主键
    @Column(name = "GOURP_NAME", nullable = false)
    private String name; //组织机构名称
    @Column(name = "GOURP_CODE", nullable = false)
    private String code; //组织结构编码
    @Column(name = "NOTES", length = 255)
    private String notes; //备注
    @Column(name = "CREATE_UID")
    private String createUid;
    @Column(name = "WRITE_UID")
    private String writeUid;
    @Column(name = "CREATE_DATE")
    private String createDate;
    @Column(name = "WRITE_DATE")
    private String writeDate;

}
