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
    @Column(name = "MODULE_ID", length = 64)
    private long id;
    @Column(name = "MODULE_NAME", length = 64)
    private String name;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "WRITE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date writeDate;
    @OneToOne
    @JoinColumn(name = "CREATE_UID")
    private User createUid;
    @OneToOne
    @JoinColumn(name = "WRITE_UID")
    private User writeUid;
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "NOTE", length = 255)
    private String note;

}
