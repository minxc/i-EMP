
/**  

* @Title: Position.java 

* @Package com.minxc.core.entity 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午8:00:59 

* @version V1.0  

*/ 

package com.minxc.core.entity;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**   
*    
* 项目名称：emp-core-model   
* 类名称：Position   
* 类描述：人力资源岗位信息
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午8:00:59   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午8:00:59   
* 修改备注：   
* @version  1.0  
*    
*/


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="HR_POSITION" )
public class Position implements Serializable {

    /** 
    
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    
    */ 
    
    
    private static final long serialVersionUID = -4347972984702973588L;
    
    @Id
    private long id;
    
    @Column(name="POSTION_NAME", length=64)
    private String name;
    
    @Column(name="POSTION_CODE", length=64)
    private String code;
    
    @Column(name="GRADE")
    private int grade;    //岗位级别
    
    @Column(name="CATEGORY", length=64)
    private String category;  //岗位类别
    @Column(name="NOTES", length=255)
    private String notes;
    @Column(name="ACTIVE", length=64)
    private boolean active; //是否启用
    
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Position parent;  //上级岗位
    
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID",foreignKey = @ForeignKey(name="FK_HR_POSITION_PARENT_ID"))
    private Set<Position> children = Sets.newHashSet();
}
