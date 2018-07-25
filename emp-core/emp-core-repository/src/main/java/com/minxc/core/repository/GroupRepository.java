package com.minxc.core.repository;

import com.minxc.core.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**********************************************************
 * GroupRepository
 * @author min.xianchang  xianchangmin@126.com
 * @date 2018/7/8
 *
 *********************************************************/
@Repository
@Transactional
public interface GroupRepository extends JpaRepository<Group, String> {
}
