package com.afdoc.domain.projectManager.entity;


import com.afdoc.domain.memberManager.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROJECT")
@Getter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "constructor_id")
    private Member constructor;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberProjectMap> members = new ArrayList<>();

    @Column(name = "name", nullable = false, unique = true, length = 30)
    private String name;

    @Column(name = "thumbnail_img_url")
    private String thumbnailImgUrl;

    @Column(name = "joined_member_cnt", nullable = false)
    private Long joinedMemberCnt;

    @Column(name = "project_type", nullable = false, length = 30)
    private String projectType;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

}
