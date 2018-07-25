package com.minxc.app.core.repository;

import com.minxc.core.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**********************************************************
 * MenuRepository
 * @author min.xianchang  xianchangmin@126.com
 * @date 2018/7/8
 *
 *********************************************************/
@Repository
@Transactional
public interface MenuRepository extends JpaRepository<Menu,String> {
}
