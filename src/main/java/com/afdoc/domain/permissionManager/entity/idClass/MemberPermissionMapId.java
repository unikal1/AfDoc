package com.afdoc.domain.permissionManager.entity.idClass;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberPermissionMapId implements Serializable {

    private Long memberId;
    private Long permissionId;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof MemberPermissionMapId that)) return true;
        return Objects.equals(memberId, that.memberId) &&
               Objects.equals(permissionId, that.permissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, permissionId);
    }
}
