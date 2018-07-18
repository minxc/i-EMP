package com.minxc.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**********************************************************
 * 定义系统提供操作（服务）
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/4
 *
 *********************************************************/
@Entity
@Table(name = "MR_ACTION", indexes = {@Index(name="IDX_MR_ACTION_VIEWID", columnList = "ACTION_VIEW_ID")})
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
    @Column(name = "ACTION_TYPE")
    private String type; //GET POST

    @Column(name = "ACTION_CODE")
    private String code;

    @Column(name = "ACTION_NAME")
    private String name;

    @Column(name = "ACTION_NOTES")
    private String notes;   //备注

    @Column(name = "ACTION_URL")
    private String url; //请求的URL

    @Column(name = "ACTION_ACTIVE")
    private boolean active;   //是否启用

    @Column(name = "ACTION_CREATE_UID")
    private String createUid;
    @Column(name = "ACTION_WRITE_UID")
    private String writeUid;
    @Column(name = "ACTION_CREATE_DATE")
    private String createDate;
    @Column(name = "ACTION_WRITE_DATE")
    private String writeDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTION_VIEW_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_MR_ACTION_VIEWID") )
    private View view;

}
