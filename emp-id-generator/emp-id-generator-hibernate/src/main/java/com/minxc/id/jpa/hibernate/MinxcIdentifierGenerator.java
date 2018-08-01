
/**  

* @Title: MinxcIdentifierGenerator.java 

* @Package com.minxc.id.jpa.hibernate 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月30日 上午12:46:07 

* @version V1.0  

*/ 

package com.minxc.id.jpa.hibernate;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;


/**   
*    
* 项目名称：emp-id-generator-hibernate   
* 类名称：MinxcIdentifierGenerator   
* 类描述:自定义主键生成策略，基于主键服务构造有顺序可破解自定义主键
* 创建人：Xianchang.min   
* 创建时间：2018年7月30日 上午12:46:07   
* 修改人：Xianchang.min   
* 修改时间：2018年7月30日 上午12:46:07   
* 修改备注：   
* @version  1.0  
*    
*/

public class MinxcIdentifierGenerator implements IdentifierGenerator {

    /* (非 Javadoc) 
    
    * <p>Title: generate</p> 
    
    * <p>Description: </p> 
    
    * @param session
    * @param object
    * @return
    * @throws HibernateException 
    
    * @see org.hibernate.id.IdentifierGenerator#generate(org.hibernate.engine.spi.SharedSessionContractImplementor, java.lang.Object) 
    
    */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        
        return null;
    }

}
