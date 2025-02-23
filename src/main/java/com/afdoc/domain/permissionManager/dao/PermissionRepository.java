package com.afdoc.domain.permissionManager.dao;


import com.afdoc.domain.permissionManager.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
