
/**  

* @Title: ModelRepository.java 

* @Package com.minxc.core.repository 

* @Description: 获取系统数据模型的服务接口

* @author Xianchang.min  

* @date 2018年7月29日 下午7:48:43 

* @version V1.0  

*/ 

package com.minxc.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minxc.core.entity.Model;


/**   
*    
* 项目名称：emp-core-repository   
* 类名称：ModelRepository   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午7:48:43   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午7:48:43   
* 修改备注：   
* @version  1.0  
*    
*/
@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

}
