package com.afdoc.domain.chatManager.entity.idClass;

import java.io.Serializable;
import java.util.Objects;

/**
 * <br>package name   : com.afdoc.domain.chatManager.entity.idClass
 * <br>file name      : ChatRoomMemberMapId
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
public class ChatRoomMemberMapId implements Serializable {
    private Long member;
    private Long chatroom;

    @Override
    public int hashCode() {
        return Objects.hash(member, chatroom);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ChatRoomMemberMapId that = (ChatRoomMemberMapId) o;
        return Objects.equals(member, that.member) &&
                Objects.equals(chatroom, that.chatroom);
    }
}
