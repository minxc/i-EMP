
/**  

* @Title: MachineIdsIdServiceImpl.java 

* @Package com.minxc.id.service.impl 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年7月29日 下午10:55:24 

* @version V1.0  

*/ 

package com.minxc.id.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.minxc.id.bean.Id;
import com.minxc.id.service.impl.populater.IdPopulator;
import com.minxc.id.service.impl.populater.ResetPopulator;
import com.minxc.id.service.impl.provider.MachineIdsProvider;

import lombok.extern.slf4j.Slf4j;

/**   
*    
* 项目名称：emp-id-impl   
* 类名称：MachineIdsIdServiceImpl   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年7月29日 下午10:55:24   
* 修改人：Xianchang.min   
* 修改时间：2018年7月29日 下午10:55:24   
* 修改备注：   
* @version  1.0  
*    
*/
@Slf4j
public class MachineIdsIdServiceImpl extends IdServiceImpl{
    
    
    protected long lastTimestamp = -1;
    protected Map<Long, Long> machineIdMap = new ConcurrentHashMap<Long, Long>();
    public static final String STORE_FILE_NAME = "machineIdInfo.store";
    private String storeFilePath;

    private File storeFile;

    private Lock lock = new ReentrantLock();

    @Override
    public void init() {
        if (!(this.machineIdProvider instanceof MachineIdsProvider)) {
            log.error("The machineIdProvider is not a MachineIdsProvider instance so that Vesta Service refuses to start.");
            throw new RuntimeException(
                    "The machineIdProvider is not a MachineIdsProvider instance so that Vesta Service refuses to start.");
        }
        super.init();
        initStoreFile();
        initMachineId();
    }

    @Override
    protected void populateId(Id id) {
        supportChangeMachineId(id);
    }

    private void supportChangeMachineId(Id id) {
        try {
            id.setMachine(this.machineId);
            idPopulator.populateId(timer, id, idMeta);
            this.lastTimestamp = id.getTime();
        } catch (IllegalStateException e) {
            log.warn("Clock moved backwards, change MachineId and reset IdPopulator");
            lock.lock();
            try {
                if (id.getMachine() == this.machineId) {
                    changeMachineId();
                    resetIdPopulator();
                }
            } finally {
                lock.unlock();
            }
            supportChangeMachineId(id);
        }
    }

    protected void changeMachineId() {
        this.machineIdMap.put(this.machineId, this.lastTimestamp);
        storeInFile();
        initMachineId();
    }

    protected void resetIdPopulator() {
        if (idPopulator instanceof ResetPopulator) {
            ((ResetPopulator) idPopulator).reset();
        } else {
            try {
                IdPopulator newIdPopulator = this.idPopulator.getClass().newInstance();
                this.idPopulator = newIdPopulator;
            } catch (InstantiationException e1) {
                throw new RuntimeException("Reset IdPopulator <[" + this.idPopulator.getClass().getCanonicalName() + "]> instance error", e1);
            } catch (IllegalAccessException e1) {
                throw new RuntimeException("Reset IdPopulator <[" + this.idPopulator.getClass().getCanonicalName() + "]> instance error", e1);
            }
        }
    }

    protected void initStoreFile() {
        if (storeFilePath == null || storeFilePath.length() == 0) {
            storeFilePath = System.getProperty("user.dir") + File.separator + STORE_FILE_NAME;
        }
        try {
            log.info("machineId info store in <[" + storeFilePath + "]>");
            storeFile = new File(storeFilePath);
            if (storeFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(storeFile));
                String line = reader.readLine();
                while (line != null && line.length() > 0) {
                    String[] kvs = line.split(":");
                    if (kvs.length == 2) {
                        this.machineIdMap.put(Long.parseLong(kvs[0]), Long.parseLong(kvs[1]));
                    } else {
                        throw new IllegalArgumentException(storeFile.getAbsolutePath() + " has illegal value <[" + line + "]>");
                    }
                    line = reader.readLine();
                }
                reader.close();
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    protected void initMachineId() {
        long startId = this.machineId;
        long newMachineId = this.machineId;
        while (true) {
            if (this.machineIdMap.containsKey(newMachineId)) {
                long timestamp = timer.genTime();
                if (this.machineIdMap.get(newMachineId) < timestamp) {
                    this.machineId = newMachineId;
                    break;
                } else {
                    newMachineId = ((MachineIdsProvider) this.machineIdProvider).getNextMachineId();
                }
                if (newMachineId == startId) {
                    throw new RuntimeException("No machineId is available");
                }
                validateMachineId(newMachineId);
            } else {
                this.machineId = newMachineId;
                break;
            }
        }
        log.warn("MachineId: {}", this.machineId);
    }

    protected void storeInFile() {
        
        Writer writer = null;
        try {
            writer = new FileWriter(storeFile, false);
            for (Map.Entry<Long, Long> entry : this.machineIdMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            log.error("Write machineId info to File<[" + storeFile.getAbsolutePath() + "]> error");
            throw new RuntimeException("Write machineId info to File<[" + storeFile.getAbsolutePath() + "]> error");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public void setStoreFilePath(String storeFilePath) {
        this.storeFilePath = storeFilePath;
    }
}
