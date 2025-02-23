package com.afdoc.domain.permissionManager.entity.mappingEntity;


import com.afdoc.domain.memberManager.entity.Member;
import com.afdoc.domain.permissionManager.entity.Permission;
import com.afdoc.domain.permissionManager.entity.idClass.MemberPermissionMapId;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER_PERMISSION_MAP")
@IdClass(MemberPermissionMapId.class)
@Getter
public class MemberPermissionMap {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    private Member member;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", referencedColumnName = "id", nullable = false)
    private Permission permission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grantor_id")
    private Member grantor;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;


}
