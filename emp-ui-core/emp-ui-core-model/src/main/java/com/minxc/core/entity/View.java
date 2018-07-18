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
@Table(name = "MR_VIEWS",indexes = {@Index(name = "IDX_MR_VIEWS_MODEL_ID", columnList = "MODEL_ID")})
public class View implements Serializable {

    private static final long serialVersionUID = 5124162375658286106L;

    @Id
    @Column(name="VIEW_ID", length = 32)
    private String id;
    @Column(name="VIEW_NAME", length = 32)
    private String name; //视图名称
    @Column(name="VIEW_CODE", length = 32)
    private String code; //视图编码
    @Column(name="VIEW_URL", length = 128)
    private String url; //视图所对应URL

    @OneToOne
    @JoinColumn(name = "MODEL_ID", foreignKey = @ForeignKey(name="FK_MR_VIEWS_MODEL_ID"))
    private Model model;   //视图对应的模型

    @OneToMany(mappedBy = "view")
    private Set<Action> actions;  //视图所包含的操作


}
