package com.afdoc.domain.projectManager.entity;


import com.afdoc.domain.memberManager.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER_PROJECT_MAP")
@Getter
public class MemberProjectMapRepository {
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

    @Column(name = "joined_at", nullable = false)
    @CreatedDate
    private LocalDateTime joinedAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
