package com.minxc.core.repository;

import com.minxc.core.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**********************************************************
 * ModuleRepository
 * @author min.xianchang  xianchangmin@126.com
 * @date 2018/7/8
 *
 *********************************************************/
@Repository
@Transactional
public interface ModuleRepository extends JpaRepository<Module, String> {
}
