package com.minxc.app.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minxc.core.entity.User;

import javax.transaction.Transactional;

/**********************************************************
 * $()
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/29
 *
 *********************************************************/

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {
}
