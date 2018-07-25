package com.minxc.core.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**********************************************************
 * $()
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/24
 *
 *********************************************************/

@Entity
@Table(name = "MR_USER_ORGNIZATION_LINK")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class UserOrganizationLink implements Serializable {

    private static final long serialVersionUID = -2247125479788756201L;

    @Id
    private String id;
    
    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    
    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ORG_ID", nullable = false)
    private Organization organization;
    
    
    @Column(name = "SEQ")
    private int sequence;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "CRETAE_DATE")
    private Date createDate;
    @Column(name = "WRITE_DATE")
    private Date writeDate;
}
