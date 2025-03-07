package com.afdoc.domain.chatManager.entity;

import com.afdoc.domain.chatManager.entity.idClass.ChatRoomMemberMapId;
import com.afdoc.domain.memberManager.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


/**
 * <br>package name   : com.afdoc.domain.chatManager.entity
 * <br>file name      : ChatroomMemberMap
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
@Table(name = "CHATROOM_MEMBER_MAP")
@IdClass(ChatRoomMemberMapId.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"member", "chatroom"})
@EntityListeners(AuditingEntityListener.class)
public class ChatroomMemberMap {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id", referencedColumnName = "id")
    private Chatroom chatroom;

    @Column(name = "joined_at")
    @CreatedDate
    private LocalDateTime joinedAt;

}
