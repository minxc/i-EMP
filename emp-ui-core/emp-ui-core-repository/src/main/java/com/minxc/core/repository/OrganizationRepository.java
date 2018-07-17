package com.minxc.core.repository;

import com.minxc.core.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**********************************************************
 *
 * @author Xcm   xianchangmin@126.com
 * @date 2018/7/3
 *
 *********************************************************/
@Repository
@Transactional
public interface OrganizationRepository extends JpaRepository<Group, String> {
}
