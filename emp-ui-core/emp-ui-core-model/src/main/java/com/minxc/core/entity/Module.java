package com.minxc.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**********************************************************
 * 系统模块
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/4
 *
 *********************************************************/
@Entity
@Table(name = "MR_MODULE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Module implements Serializable {
    private static final long serialVersionUID = 3698217265888327346L;
    @Id
    @Column(name = "MODULE_ID")
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "WRITE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date writeAt;
    @OneToOne
    @JoinColumn(name = "CREATE_BY")
    private User createBy;
    @OneToOne
    @JoinColumn(name = "WRITE_BY")
    private User writeBy;
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "NOTE", length = 255)
    private String note;

}
