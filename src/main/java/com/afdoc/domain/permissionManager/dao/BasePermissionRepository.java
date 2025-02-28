package com.afdoc.domain.permissionManager.dao;

import com.afdoc.domain.permissionManager.entity.BasePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BasePermissionRepository extends JpaRepository<BasePermission, Long> {
}
