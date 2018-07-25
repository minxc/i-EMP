package com.minxc.app.core.repository;

import com.minxc.core.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**********************************************************
 * ActionRepository
 * @author min.xianchang  xianchangmin@126.com
 * @date 2018/7/8
 *
 *********************************************************/

@Repository
@Transactional
public interface ActionRepository extends JpaRepository<Action, String> {

}
