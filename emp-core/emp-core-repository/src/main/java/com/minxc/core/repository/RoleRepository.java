package com.minxc.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minxc.core.entity.Role;


/**
 * 
*    
* 项目名称：emp-core-repository   
* 类名称：RoleRepository   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午7:46:16   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午7:46:16   
* 修改备注：   
* @version  1.0  
*
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
