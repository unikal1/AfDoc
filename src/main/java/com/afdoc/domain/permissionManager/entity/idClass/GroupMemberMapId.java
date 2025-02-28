package com.afdoc.domain.permissionManager.entity.idClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GroupMemberMapId implements Serializable {

    private Long member;

    private Long permissionGroup;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMemberMapId that = (GroupMemberMapId) o;
        return Objects.equals(member, that.member) &&
               Objects.equals(permissionGroup, that.permissionGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, permissionGroup);
    }
}
