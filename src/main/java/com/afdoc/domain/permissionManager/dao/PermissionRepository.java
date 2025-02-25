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



    Optional<Permission> findByProjectAndBasePermission_Name(Project project, String name);

        @Query("""
        SELECT DISTINCT pgpm.permission
        FROM PermissionGroupPermissionMap pgpm
        JOIN pgpm.permissionGroup pg
        JOIN pg.members pgm
        WHERE pg.project = :project
          AND pgm.member = :member
    """)
    List<Permission> findDistinctPermissionsByProjectAndMember(
            @Param("project") Project project,
            @Param("member") Member member
    );



}
