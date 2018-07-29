
/**  

* @Title: IdConverterImpl.java 

* @Package com.minxc.id.service.impl.converter 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午11:01:36 

* @version V1.0  

*/ 

package com.minxc.id.service.impl.converter;

import com.minxc.id.bean.Id;
import com.minxc.id.service.impl.bean.IdMeta;

/**   
*    
* 项目名称：emp-id-impl   
* 类名称：IdConverterImpl   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午11:01:36   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午11:01:36   
* 修改备注：   
* @version  1.0  
*    
*/

public class IdConverterImpl implements IdConverter {
    public IdConverterImpl() {
    }

    public long convert(Id id, IdMeta idMeta) {
        return doConvert(id, idMeta);
    }

    protected long doConvert(Id id, IdMeta idMeta) {
        long ret = 0;

        ret |= id.getMachine();

        ret |= id.getSeq() << idMeta.getSeqBitsStartPos();

        ret |= id.getTime() << idMeta.getTimeBitsStartPos();

        ret |= id.getGenMethod() << idMeta.getGenMethodBitsStartPos();

        ret |= id.getType() << idMeta.getTypeBitsStartPos();

        ret |= id.getVersion() << idMeta.getVersionBitsStartPos();

        return ret;
    }

    public Id convert(long id, IdMeta idMeta) {
        return doConvert(id, idMeta);
    }

    protected Id doConvert(long id, IdMeta idMeta) {
        Id ret = new Id();

        ret.setMachine(id & idMeta.getMachineBitsMask());

        ret.setSeq((id >>> idMeta.getSeqBitsStartPos()) & idMeta.getSeqBitsMask());

        ret.setTime((id >>> idMeta.getTimeBitsStartPos()) & idMeta.getTimeBitsMask());

        ret.setGenMethod((id >>> idMeta.getGenMethodBitsStartPos()) & idMeta.getGenMethodBitsMask());

        ret.setType((id >>> idMeta.getTypeBitsStartPos()) & idMeta.getTypeBitsMask());

        ret.setVersion((id >>> idMeta.getVersionBitsStartPos()) & idMeta.getVersionBitsMask());

        return ret;
    }

   
}
