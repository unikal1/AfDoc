package com.afdoc.domain.permissionManager.dao;


import com.afdoc.domain.permissionManager.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);

        @Query("""
        SELECT DISTINCT pgp.permission
        FROM PermissionGroup pg
        JOIN pg.members gm
        JOIN pg.permissions pgp
        WHERE pg.project.id = :projectId
          AND gm.member.id = :memberId
    """)
    List<Permission> findDistinctPermissionsByProjectAndMember(
            @Param("projectId") Long projectId,
            @Param("memberId") Long memberId
    );



}
