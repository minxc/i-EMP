package com.minxc.core.entity;

import com.google.common.collect.Sets;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**********************************************************
 * 系统菜单
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/4
 *
 *********************************************************/

@Entity
@Table(name="MR_MENUS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Menu implements Serializable {

    private static final long serialVersionUID = 6966767403824381968L;
    @Id
    @Column(name="MENU_ID")
    private String id;
    @Column(name = "MENU_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Menu parent;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PARENT_ID")
    private Set<Menu> children = Sets.newHashSet();

    @OneToOne
    @JoinColumn(name = "VIEW_ID")
    private View view;   //菜单所对应的视图

    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "WRITE_DATE")
    private Date writeDate;
    @Column(name="CREATE_UID")
    private String createUid; //创建人
    @Column(name="WRITE_UID")
    private String writeUid; //上次更新人
}
