package com.afdoc.domain.permissionManager.entity;


import com.afdoc.domain.permissionManager.entity.mappingEntity.GroupMemberMap;
import com.afdoc.domain.permissionManager.entity.mappingEntity.PermissionGroupPermissionMap;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERMISSION_GROUP")
@Getter
public class PermissionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "permission_group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupMemberMap> members = new ArrayList<>();

    @OneToMany(mappedBy = "permission_group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PermissionGroupPermissionMap> permissions = new ArrayList<>();

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
