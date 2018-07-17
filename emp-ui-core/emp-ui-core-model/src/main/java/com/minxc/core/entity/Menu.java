package com.minxc.core.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**********************************************************
 * 系统菜单
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/4
 *
 *********************************************************/

@Entity(name="MR_MENUS")
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
    private Action action;
    private Menu parent;



}
