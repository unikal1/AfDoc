package com.afdoc.domain.permissionManager.dao;


import com.afdoc.domain.memberManager.entity.Member;
import com.afdoc.domain.permissionManager.entity.Permission;
import com.afdoc.domain.projectManager.entity.Project;
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
        WHERE pg.project = :project
          AND gm.member= :member
    """)
    List<Permission> findDistinctPermissionsByProjectAndMember(
            @Param("project") Project project,
            @Param("member") Member member
    );



}
