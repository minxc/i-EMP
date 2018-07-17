package com.minxc.core.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**********************************************************
 * 定义系统提供操作（服务）
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/4
 *
 *********************************************************/
@Entity
@Table(name="MR_ACTION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Action implements Serializable {

    private static final long serialVersionUID = 6223171507632459830L;

    @Id
    private String id;
    @Column(name="TYPE")
    private String type; //计划提供使用类型区分前台操作、后台请求、或者页面请求信息

    @Column(name="CODE")
    private String code;

    @Column(name="NAME")
    private String name;
    @Column(name="NOTE")
    private String notes;
}
