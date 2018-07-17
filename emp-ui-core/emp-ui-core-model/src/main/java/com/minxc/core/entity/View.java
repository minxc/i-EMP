package com.minxc.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**********************************************************
 * 系统界面信息
 * 表示系统的 页面，末班、报表等信息
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/4
 *
 *********************************************************/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "MR_VIEWS")
public class View implements Serializable {

    private static final long serialVersionUID = 5124162375658286106L;

    @Id
    private String id;
    @OneToOne
    private Model model;
    @OneToMany
    private Set<Action> actions;


}
