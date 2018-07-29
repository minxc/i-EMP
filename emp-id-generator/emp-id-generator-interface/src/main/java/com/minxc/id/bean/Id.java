
/**  

* @Title: Id.java 

* @Package com.minxc.id.bean 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午9:51:31 

* @version V1.0  

*/ 

package com.minxc.id.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**   
*    
* 项目名称：emp-id-interface   
* 类名称：Id   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午9:51:31   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午9:51:31   
* 修改备注：   
* @version  1.0  
*    
*/


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Id implements Serializable{

    /** 
    
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    
    */ 
    private static final long serialVersionUID = 4108245830335142243L;
    
    private long machine;   //服务机器编号
    private long seq;     //顺序号
    private long time;    //系统运行时间
    private long genMethod;  //Id产生方式
    private long type;      //部署方式
    private long version;   //版本
    


   
}
