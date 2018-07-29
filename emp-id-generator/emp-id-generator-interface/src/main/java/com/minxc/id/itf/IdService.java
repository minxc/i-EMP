
/**  

* @Title: IdService.java 

* @Package com.minxc.id.itf 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午9:49:51 

* @version V1.0  

*/ 

package com.minxc.id.itf;

import java.util.Date;

import com.minxc.id.bean.Id;

/**   
*    
* 项目名称：emp-id-interface   
* 类名称：IdService   
* 类描述： 主键生成服务接口
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午9:49:51   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午9:49:51   
* 修改备注：   
* @version  1.0  
*    
*/

public interface IdService {
    
    public long genId();

    public Id expId(long id);

    public long makeId(long time, long seq);

    public long makeId(long time, long seq, long machine);

    public long makeId(long genMethod, long time, long seq, long machine);

    public long makeId(long type, long genMethod, long time,
                       long seq, long machine);

    public long makeId(long version, long type, long genMethod,
                       long time, long seq, long machine);

    public Date transTime(long time);
}
