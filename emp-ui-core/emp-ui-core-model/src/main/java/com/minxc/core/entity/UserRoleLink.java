package com.minxc.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**********************************************************
 * 用户角色关联实体类
 * 
 * @author Xcm xianchangmin@126.com
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
	@Column(name ="ID")
	private String id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name = "ROLE_ID", nullable = false)
	private Role role;
	private boolean active;

	@Column(name = "CREATE_DATE")
	private Date createDate;
	@Column(name = "WRITE_DATE")
	private Date writeDate;
	@Column(name = "CREATE_UID")
	private String createUid; // 创建人
	@Column(name = "WRITE_UID")
	private String writeUid; // 上次更新人
}
