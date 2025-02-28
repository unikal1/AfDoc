package com.afdoc.domain.permissionManager.dao;


import com.afdoc.domain.permissionManager.entity.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {

}
