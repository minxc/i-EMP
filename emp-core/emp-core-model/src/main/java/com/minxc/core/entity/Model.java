package com.minxc.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**********************************************************
 * 系统所提供数据模型
 * 
 * @author Xcm xianchangmin@126.com
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
	@Column(name = "MODEL_ID", length = 64)
	private String id;
	@Column(name = "MODEL_NAME", length = 64)
	private String name;
	@Column(name = "STATE")
	private String state;
	@Column(name = "INFO", length = 255)
	private String info;
	@Column(name = "IS_TRANSIENT")
	private Boolean isTransient;
	@Column(name = "CREATE_UID", length = 64)
	private String createUid;
	@Column(name = "WRITE_UID", length = 64)
	private String writeUid;
	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Column(name = "WRITE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date writeDate;

}
