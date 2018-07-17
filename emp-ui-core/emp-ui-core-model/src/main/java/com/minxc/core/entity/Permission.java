package com.minxc.core.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**********************************************************
 * $()
 * @author Xcm   xianchangmin@126.com
 * @date 2018/5/31
 *
 *********************************************************/
@Entity
@Table(name="MR_PERMISSION")
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Permission implements Serializable {

    private static final long serialVersionUID = -3544380775368936034L;

    @Id
    private String id; //主键
    private String name; //组织机构名称
    private String code; //组织结构编码
    private Integer level; //组织结构层级
    private String path; //组织结构路径
    private String note; //组织结构备注

}
