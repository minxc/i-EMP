
/**  

* @Title: IdServiceImpl.java 

* @Package com.minxc.id.service.impl 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午10:53:44 

* @version V1.0  

*/ 

package com.minxc.id.service.impl;

import com.minxc.id.bean.Id;
import com.minxc.id.service.impl.bean.IdType;
import com.minxc.id.service.impl.populater.AtomicIdPopulator;
import com.minxc.id.service.impl.populater.IdPopulator;
import com.minxc.id.service.impl.populater.LockIdPopulator;
import com.minxc.id.service.impl.populater.SyncIdPopulator;
import com.minxc.id.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

/**   
*    
* 项目名称：emp-id-impl   
* 类名称：IdServiceImpl   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午10:53:44   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午10:53:44   
* 修改备注：   
* @version  1.0  
*    
*/
@Slf4j
public class IdServiceImpl extends AbstractIdServiceImpl{

    private static final String SYNC_LOCK_IMPL_KEY = "vesta.sync.lock.impl.key";

    private static final String ATOMIC_IMPL_KEY = "vesta.atomic.impl.key";

    protected IdPopulator idPopulator;

    public IdServiceImpl() {
        super();
    }

    public IdServiceImpl(String type) {
        super(type);
    }

    public IdServiceImpl(long type) {
        super(type);
    }

    public IdServiceImpl(IdType type) {
        super(type);
    }

    @Override
    public void init() {
        super.init();
        initPopulator();
    }

    public void initPopulator() {
        if (idPopulator != null) {
            log.info("The " + idPopulator.getClass().getCanonicalName() + " is used.");
        } else if (CommonUtils.isPropKeyOn(SYNC_LOCK_IMPL_KEY)) {
            log.info("The SyncIdPopulator is used.");
            idPopulator = new SyncIdPopulator();
        } else if (CommonUtils.isPropKeyOn(ATOMIC_IMPL_KEY)) {
            log.info("The AtomicIdPopulator is used.");
            idPopulator = new AtomicIdPopulator();
        } else {
            log.info("The default LockIdPopulator is used.");
            idPopulator = new LockIdPopulator();
        }
    }

    protected void populateId(Id id) {
        idPopulator.populateId(timer, id, idMeta);
    }

    public void setIdPopulator(IdPopulator idPopulator) {
        this.idPopulator = idPopulator;
    }
}
