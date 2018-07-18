package com.minxc.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**********************************************************
 * 用户角色关联实体类
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/13
 *
 *******************************************************/

@Entity
@Table(name = "MR_USER_ROLE_LINKS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class UserRoleLink implements Serializable {

    private static final long serialVersionUID = -693476254671853756L;
    @Id
    private String id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="USER_ID", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name ="ROLE_ID", nullable = false)
    private Role role;
    private boolean active;
    private String  createBy;
    private String createAt;
    private String writeBy;
    private String writeAt;
}
