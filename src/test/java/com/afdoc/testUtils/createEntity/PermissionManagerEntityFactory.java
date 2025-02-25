package com.afdoc.testUtils.createEntity;

import com.afdoc.domain.memberManager.entity.Member;
import com.afdoc.domain.permissionManager.entity.BasePermission;
import com.afdoc.domain.permissionManager.entity.Permission;
import com.afdoc.domain.permissionManager.entity.PermissionGroup;
import com.afdoc.domain.projectManager.entity.Project;

/**
 * Utility class for creating Permission and PermissionGroup objects with flexible parameter combinations.
 * Default values are applied when parameters are not provided.
 *
 * <h3>Default values:</h3>
 * <ul>
 *   <li>category: "category"</li>
 *   <li>targetId: 1L</li>
 *   <li>permissionName: "permission_name"</li>
 *   <li>description: "default_description"</li>
 *   <li>permissionGroupName: "permission_group_name"</li>
 *   <li>Project (if not provided): Created using ProjectManagerEntityFactory.createProject()</li>
 * </ul>
 */
public class PermissionManagerEntityFactory {

    public static final String category = "category";
    public static final Long targetId = 1L;
    public static final String permissionName = "permission_name";
    public static final String description = "default_description";
    public static final String permissionGroupName = "permission_group_name";

    // ====================== BasePermission ======================
    public static BasePermission createBasePermission(String category, String name) {
        return BasePermission.builder()
                .category(category)
                .name(name)
                .build();
    }

    public static BasePermission createBasePermission() {
        return createBasePermission(category, permissionName);
    }


    // ======================== Permission ========================

    /**
     * Creates a Permission with the given project, category, targetId, and name.
     *
     * @param project   The associated Project.
     * @param basePermission The base permission to add.
     * @return A Permission object.
     */
    public static Permission createPermission(BasePermission basePermission, Project project, Long targetId) {
        return Permission.builder()
                .project(project)
                .basePermission(basePermission)
                .targetId(targetId)
                .build();
    }

    public static Permission createPermission(BasePermission basePermission, Project project) {
        return Permission.builder()
                .project(project)
                .basePermission(basePermission)
                .targetId(targetId)
                .build();
    }

    // ======================== PermissionGroup ========================

    /**
     * Creates a PermissionGroup with the provided member, permission constructor, permission, and name.
     *
     * @param member               The Member to be added to the group.
     * @param permissionConstructor The Member who creates the Permission.
     * @param permission           The Permission added to the group.
     * @param name                 The name of the PermissionGroup.
     * @return A PermissionGroup object.
     */
    public static PermissionGroup createPermissionGroup(Project project, Member member, Member permissionConstructor, Permission permission,
                                                        String name) {
        PermissionGroup permissionGroup = PermissionGroup.builder()
                .name(name)
                .description(description)
                .build();
        permissionGroup.addMember(member);
        permissionGroup.addPermission(permission, permissionConstructor);

        project.addPermissionGroup(permissionGroup);
        return permissionGroup;
    }

    public static PermissionGroup createPermissionGroup(Project project, String name) {
        return PermissionGroup.builder()
                .project(project)
                .name(name)
                .build();
    }
    /**
     * Creates a PermissionGroup using the same Member as both the group member and permission constructor.
     *
     * @param member     The Member to be added to the group and acts as the permission constructor.
     * @param permission The Permission added to the group.
     * @param name       The name of the PermissionGroup.
     * @return A PermissionGroup object.
     */
    public static PermissionGroup createPermissionGroup(Project project, Member member, Permission permission, String name) {
        return createPermissionGroup(project, member, member, permission, name);
    }

    /**
     * Creates a PermissionGroup with default values.
     * Default values: name = "permission_group_name".
     *
     * @param member     The Member to be added to the group.
     * @param permission The Permission added to the group.
     * @return A PermissionGroup object.
     */
    public static PermissionGroup createPermissionGroup(Project project, Member member, Permission permission) {
        return createPermissionGroup(project, member, permission, permissionGroupName);
    }
}
