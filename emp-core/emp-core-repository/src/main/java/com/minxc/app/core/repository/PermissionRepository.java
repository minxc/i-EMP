package com.minxc.app.core.repository;

import com.minxc.core.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**********************************************************
 * PermissionRepository
 * @author min.xianchang  xianchangmin@126.com
 * @date 2018/7/8
 *
 *********************************************************/
public interface PermissionRepository extends JpaRepository<Permission, String> {
}
