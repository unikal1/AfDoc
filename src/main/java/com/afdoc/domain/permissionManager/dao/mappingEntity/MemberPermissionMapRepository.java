package com.afdoc.domain.permissionManager.dao.mappingEntity;

import com.afdoc.domain.permissionManager.entity.idClass.MemberPermissionMapId;
import com.afdoc.domain.permissionManager.entity.mappingEntity.MemberPermissionMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPermissionMapRepository extends JpaRepository<MemberPermissionMap, MemberPermissionMapId> {
}
