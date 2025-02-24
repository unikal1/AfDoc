package com.afdoc.testUtils.createEntity;

import com.afdoc.domain.memberManager.entity.Member;
import com.afdoc.domain.projectManager.entity.Project;


/**
 * Utility methods for creating ProjectManager objects with different parameter combinations.
 * Overloaded methods provide flexibility, with default values used where applicable.
 *<pre>
 * Default values:
 * [Project.java]
 * - imgUrl: "example.com"
 * - projectType: "project_type"
 * - projectName (when not provided): "project_name"
 * - constructor (when not provided): Created using MemberManagerEntityFactory.createMember()
 * </pre>
 */
public class ProjectManagerEntityFactory {

    public static final String imgUrl = "example.com";
    public static final String projectType = "project_type";
    public static final String projectName = "project_name";
    public static final String description = "default_description";



    /**
     * Creates a Project using the provided Member, name, imgUrl, and projectType.
     * @param constructor The Member who constructs the project.
     * @param projectName The name of the project.
     * @param imgUrl The URL of the project's thumbnail image.
     * @param projectType The type of the project.
     * @return A newly created Project object.
     */
    public static Project createProject(Member constructor, String projectName, String imgUrl, String projectType) {
        Project project = Project.builder()
                .constructor(constructor)
                .name(projectName)
                .thumbnailImgUrl(imgUrl)
                .projectType(projectType)
                .description(description)
                .build();

        project.addMember(constructor);
        return project;
    }

    /**
     * Creates a Project using the constructor's username, name, imgUrl, and projectType.
     * The Member object is created using MemberManagerEntityFactory.createMember(constructorUsername).
     * @param constructorUsername The username of the Member who constructs the project.
     * @param projectName The name of the project.
     * @param imgUrl The URL of the project's thumbnail image.
     * @param projectType The type of the project.
     * @return A newly created Project object.
     */
    public static Project createProject(String constructorUsername, String projectName, String imgUrl, String projectType) {
        Member member = MemberManagerEntityFactory.createMember(constructorUsername);
        return createProject(member, projectName, imgUrl, projectType);
    }

    /**
     * Creates a Project using the constructor's username and name.
     * Default values:
     * - imgUrl: "example.com"
     * - projectType: "project_type"
     * @param constructorUsername The username of the Member who constructs the project.
     * @param projectName The name of the project.
     * @return A newly created Project object.
     */
    public static Project createProject(String constructorUsername, String projectName) {
        Member member = MemberManagerEntityFactory.createMember(constructorUsername);
        return createProject(member, projectName, imgUrl, projectType);
    }

    /**
     * Creates a Project using the provided Member and name.
     * Default values:
     * - imgUrl: "example.com"
     * - projectType: "project_type"
     * @param constructor The Member who constructs the project.
     * @param projectName The name of the project.
     * @return A newly created Project object.
     */
    public static Project createProject(Member constructor, String projectName) {
        return createProject(constructor, projectName, imgUrl,  projectType);
    }

    /**
     * Creates a Project using default values:
     * - constructor: Created using MemberManagerEntityFactory.createMember()
     * - projectName: "project_name"
     * - imgUrl: "example.com"
     * - projectType: "project_type"
     * @param member The member who construct and join.
     * @return A newly created Project object.
     */
    public static Project createProject(Member member) {
        return createProject(member, projectName);
    }
}
