package com.minxc.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**********************************************************
 * $()
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
    @Column(name = "NAME", length = 128)
    private String name; //组织机构名称
    @Column(name = "CODE", length = 64)
    private String code; //组织结构编码
    @Column(name = "NOTES")
    private String note; //组织结构备注

    @Column(name = "CREATE_UID")
    private String createUid;
    @Column(name = "WRITE_UID")
    private String writeUid;
    @Column(name = "CREATE_DATE")
    private String createDate;
    @Column(name = "WRITE_DATE")
    private String writeDate;

}
