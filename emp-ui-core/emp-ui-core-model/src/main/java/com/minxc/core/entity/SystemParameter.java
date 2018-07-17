package com.minxc.core.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**********************************************************
 * 系统参数设置表
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/24
 *
 *********************************************************/
@Entity
@Table(name="MR_SYSTEM_PARAMETERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class SystemParameter implements Serializable {

    private static final long serialVersionUID = 6530416528271673220L;
    @Id
    private String  id;
    private String name;
    private String code;
    private String value;
    private String note;
    private boolean active;
}
