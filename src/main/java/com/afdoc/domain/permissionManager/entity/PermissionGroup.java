package com.afdoc.domain.permissionManager.entity;


import com.afdoc.domain.memberManager.entity.Member;
import com.afdoc.domain.permissionManager.entity.mappingEntity.GroupMemberMap;
import com.afdoc.domain.permissionManager.entity.mappingEntity.PermissionGroupPermissionMap;
import com.afdoc.domain.projectManager.entity.Project;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERMISSION_GROUP")
@Getter
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class PermissionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @OneToMany(mappedBy = "permissionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<GroupMemberMap> members = new ArrayList<>();

    @OneToMany(mappedBy = "permissionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PermissionGroupPermissionMap> permissions = new ArrayList<>();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public boolean addMember(Member member) {
        if(member.getId() == null) return false;
        GroupMemberMap groupMemberMap = new GroupMemberMap(member, this);

        if(!members.contains(groupMemberMap)) {
            members.add(groupMemberMap);
            return true;
        }
        return false;
    }

    public boolean removeMember(Member member) {
        if(member.getId() == null) return false;
        GroupMemberMap groupMemberMap = new GroupMemberMap(member, this);
        if(!members.contains(groupMemberMap)) {
            members.remove(groupMemberMap);
            return true;
        }
        return false;
    }

    public boolean addPermission(Permission permission, Member constructor) {
        if(permission.getId() == null) return false;
        if(constructor.getId() == null) return false;
        PermissionGroupPermissionMap permissionGroupPermissionMap =
                new PermissionGroupPermissionMap(this, permission, constructor);
        if(!permissions.contains(permissionGroupPermissionMap)) {
            permissions.add(permissionGroupPermissionMap);
            return true;
        }
        return false;
    }

    public boolean removePermission(Permission permission) {
        if(permission.getId() == null) return false;
        PermissionGroupPermissionMap permissionGroupPermissionMap =
                new PermissionGroupPermissionMap(this, permission, null);
        if(!permissions.contains(permissionGroupPermissionMap)) {
            permissions.remove(permissionGroupPermissionMap);
            return true;
        }
        return false;
    }



}
