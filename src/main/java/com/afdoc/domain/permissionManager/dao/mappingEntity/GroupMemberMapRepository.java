package com.afdoc.domain.permissionManager.dao.mappingEntity;

import com.afdoc.domain.permissionManager.entity.idClass.GroupMemberMapId;
import com.afdoc.domain.permissionManager.entity.mappingEntity.GroupMemberMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberMapRepository extends JpaRepository<GroupMemberMap, GroupMemberMapId> {
}
