package com.afdoc.domain.permissionManager.dao.mappingEntity;

import com.afdoc.domain.permissionManager.entity.idClass.PermissionGroupPermissionMapId;
import com.afdoc.domain.permissionManager.entity.mappingEntity.PermissionGroupPermissionMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionGroupPermissionMapRepository
        extends JpaRepository<PermissionGroupPermissionMap, PermissionGroupPermissionMapId> {
}
