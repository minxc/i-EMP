package com.minxc.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**********************************************************
 * 系统所提供数据模型
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/4
 *
 *********************************************************/
@Entity
@Table(name = "MR_MODELS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Model implements Serializable {

    private static final long serialVersionUID = 3765327366122953661L;
    @Id
    private String id;
    @Column(name="MODEL_NAME")
    private String name;
    @Column(name="STATE")
    private String state;
    @Column(name="INFO")
    private String info;
    @Column(name="IS_TRANSIENT")
    private Boolean isTransient;
    @Column(name="CREATE_UID")
    private String createUid;
    @Column(name="UPDATE_UID")
    private String updateUid;
    @Column(name="CREATE_DATE")
    private String createDate;
    @Column(name="UPDATE_DATE")
    private String updateDate;


}
