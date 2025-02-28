package com.afdoc.testUtils.createEntity;

import com.afdoc.domain.memberManager.entity.Member;

/**
 * Utility methods for creating MemberManager objects with different parameter combinations.
 * Overloaded methods provide flexibility, with default values used where applicable.
 * <pre>
 * Default values:
 *  [Member.java]
 * - password: "password"
 * - name: "john"
 * - imgUrl: "example.com"
 * - username (when not provided): "username"
 * </pre>
 */
public class MemberManagerEntityFactory {

    public static final String username = "username";
    public static final String name = "john";
    public static final String password = "password";
    public static final String imgUrl = "example.com";

    /**
     * Creates a Member using the provided username, password, name, and imgUrl.
     * @param username The username of the Member.
     * @param password The password of the Member.
     * @param name The name of the Member.
     * @param imgUrl The URL of the Member's profile image.
     * @return A newly created Member object.
     */
    public static Member createMember(String username, String password, String name, String imgUrl) {
        return Member.builder()
                .username(username)
                .password(password)
                .name(name)
                .imgUrl(imgUrl)
                .build();
    }

    /**
     * Creates a Member using the provided username.
     * Default values:
     * - password: "password"
     * - name: "john"
     * - imgUrl: "example.com"
     * @param username The username of the Member.
     * @return A newly created Member object.
     */
    public static Member createMember(String username) {
        return createMember(username, password, name, imgUrl);
    }

    /**
     * Creates a Member using default values:
     * - username: "username"
     * - password: "password"
     * - name: "john"
     * - imgUrl: "example.com"
     * @return A newly created Member object.
     */
    public static Member createMember() {
        return createMember(username);
    }
}