package com.afdoc.domain.projectManager.entity;


import com.afdoc.domain.memberManager.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER_PROJECT_MAP")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"member", "project"})
@EntityListeners(AuditingEntityListener.class)
public class MemberProjectMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "shown_name", nullable = true) //if null / then default username replace maybe...
    private String shownName;

    @Column(name = "shown_img_url", nullable = true)
    private String shownImgUrl;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "joined_at")
    @CreatedDate
    private LocalDateTime joinedAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;


    @Builder
    public MemberProjectMap(Member member, Project project, String shownName, String shownImgUrl) {
        this.shownName = (shownName != null) ? shownName : member.getName();
        this.shownImgUrl = (shownImgUrl != null) ? shownImgUrl : member.getImgUrl();

        this.member = member;
        this.project = project;
    }


}
