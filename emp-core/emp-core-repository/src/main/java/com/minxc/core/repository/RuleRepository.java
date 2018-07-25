package com.minxc.core.repository;

import com.minxc.core.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**********************************************************
 * RuleRepository
 * @author Xcm   xianchangmin@126.com
 * @date 2018/7/8
 *
 *********************************************************/
@Repository
@Transactional
public interface RuleRepository extends JpaRepository<Rule, String> {
}
