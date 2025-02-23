package com.afdoc.domain.permissionManager.entity.idClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionGroupPermissionMapId implements Serializable {

    private Long groupId;
    private Long permissionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PermissionGroupPermissionMapId that)) return false;
        return Objects.equals(groupId, that.groupId) &&
               Objects.equals(permissionId, that.permissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, permissionId);
    }
}
