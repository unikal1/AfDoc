package com.afdoc.domain.permissionManager.entity.mappingEntity;


import com.afdoc.domain.memberManager.entity.Member;
import com.afdoc.domain.permissionManager.entity.Permission;
import com.afdoc.domain.permissionManager.entity.PermissionGroup;
import com.afdoc.domain.permissionManager.entity.idClass.PermissionGroupPermissionMapId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "PERMISSION_GROUP_PERMISSION_MAP")
@IdClass(PermissionGroupPermissionMapId.class)
@Getter
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = {"permission", "permissionGroup"})
@NoArgsConstructor
public class PermissionGroupPermissionMap {

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private PermissionGroup permissionGroup;

    @Id
    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "id", nullable = false)
    private Permission permission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "constructor_id")
    private Member constructor;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public PermissionGroupPermissionMap(PermissionGroup permissionGroup, Permission permission, Member constructor) {
        this.permissionGroup = permissionGroup;
        this.permission = permission;
        this.constructor = constructor;
    }

}
