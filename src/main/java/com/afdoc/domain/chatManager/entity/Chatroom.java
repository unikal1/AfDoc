package com.afdoc.domain.chatManager.entity;

import com.afdoc.domain.memberManager.entity.Member;
import com.afdoc.domain.permissionManager.entity.Permission;
import com.afdoc.domain.projectManager.entity.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <br>package name   : com.afdoc.domain.chatManager.entity
 * <br>file name      : Chatroom
 * <br>date           : 3/7/25
 * <pre>
 * <span style="color: white;">[description]</span>
 *
 * </pre>
 * <pre>
 * <span style="color: white;">usage:</span>
 * {@code
 *
 * } </pre>
 */

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member owner;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatroomMemberMap> members = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "read_permission_id")
    private Permission readPermission;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "write_permission_id")
    private Permission writePermission;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invite_permssion_id")
    private Permission invitePermission;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_permisison_id")
    private Permission adminPermission;


}
