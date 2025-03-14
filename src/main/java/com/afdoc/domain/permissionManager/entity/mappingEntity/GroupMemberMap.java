package com.afdoc.domain.permissionManager.entity.mappingEntity;


import com.afdoc.domain.memberManager.entity.Member;
import com.afdoc.domain.permissionManager.entity.PermissionGroup;
import com.afdoc.domain.permissionManager.entity.idClass.GroupMemberMapId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "GROUP_MEMBER_MAP")
@IdClass(GroupMemberMapId.class)
@Getter
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = {"member", "permissionGroup"})
@NoArgsConstructor
public class GroupMemberMap {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_group_id", referencedColumnName = "id")
    private PermissionGroup permissionGroup;

    @Column(name = "joined_at")
    @CreatedDate
    private LocalDateTime joinedAt;

    public GroupMemberMap(Member member, PermissionGroup permissionGroup) {
        this.member = member;
        this.permissionGroup = permissionGroup;
    }

}
