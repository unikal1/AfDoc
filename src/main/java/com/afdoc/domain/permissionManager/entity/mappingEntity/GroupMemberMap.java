package com.afdoc.domain.permissionManager.entity.mappingEntity;


import com.afdoc.domain.memberManager.entity.Member;
import com.afdoc.domain.permissionManager.entity.PermissionGroup;
import com.afdoc.domain.permissionManager.entity.idClass.GroupMemberMapId;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "GROUP_MEMBER_MAP")
@IdClass(GroupMemberMapId.class)
@Getter
public class GroupMemberMap {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_group_id", referencedColumnName = "id")
    private PermissionGroup permissionGroup;

    @Column(name = "joined_at", nullable = false)
    @CreatedDate
    private LocalDateTime joinedAt;

}
