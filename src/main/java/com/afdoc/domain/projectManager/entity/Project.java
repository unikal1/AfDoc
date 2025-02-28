package com.afdoc.domain.projectManager.entity;


import com.afdoc.domain.memberManager.entity.Member;
import com.afdoc.domain.permissionManager.entity.PermissionGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROJECT")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "constructor_id")
    private Member constructor;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberProjectMap> members = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PermissionGroup> permissionGroups = new ArrayList<>();

    @Column(name = "name", nullable = false, unique = true, length = 30)
    private String name;

    @Column(name = "thumbnail_img_url")
    private String thumbnailImgUrl;

    @Column(name = "joined_member_cnt", nullable = false)
    @Builder.Default
    private Long joinedMemberCnt = 0L;

    @Column(name = "project_type", nullable = false, length = 30)
    private String projectType;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    public boolean addMember(Member member, String shownName, String shownImgUrl) {
        if(member.getId() == null) return false;
        MemberProjectMap memberProjectMap = MemberProjectMap.builder()
                .member(member)
                .project(this)
                .shownName(shownName)
                .shownImgUrl(shownImgUrl)
                .build();

        if(!members.contains(memberProjectMap)) {
            members.add(memberProjectMap);
            joinedMemberCnt++;
            return true;
        } else {
            return false;
        }
    }

    public boolean addMember(Member member) {

        return addMember(member, null, null);
    }

    public boolean addPermissionGroup(PermissionGroup permissionGroup) {
        if(permissionGroup.getId() == null) return false;
        if(!permissionGroups.contains(permissionGroup)) {
            permissionGroups.add(permissionGroup);
            permissionGroup.setProject(this);
            return true;
        }
        return false;
    }


}
