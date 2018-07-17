package com.minxc.core.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @Column(name="USERID", nullable = false)
    private User userId;
    @Column(name="ORGID", nullable = false)
    private Organization orgId;
    @Column(name = "SEQ")
    private int sequence;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "CRETAE_DATE")
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
}
