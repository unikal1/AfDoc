package com.afdoc.domain.permissionManager.entity;

import com.afdoc.domain.memberManager.dao.MemberRepository;
import com.afdoc.domain.memberManager.entity.Member;
import com.afdoc.domain.permissionManager.dao.PermissionGroupRepository;
import com.afdoc.domain.permissionManager.dao.PermissionRepository;
import com.afdoc.domain.projectManager.dao.ProjectRepository;
import com.afdoc.domain.projectManager.entity.Project;
import com.afdoc.testUtils.createEntity.MemberManagerEntityFactory;
import com.afdoc.testUtils.createEntity.PermissionManagerEntityFactory;
import com.afdoc.testUtils.createEntity.ProjectManagerEntityFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@DisplayName("Permission 엔티티 테스트")
@ActiveProfiles("test")
class PermissionTest {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PermissionGroupRepository permissionGroupRepository;


    @Test
    @DisplayName("Project 생성 후 Permission 생성")
    void createPermission() {
        //given
        String permissionName = PermissionManagerEntityFactory.permissionName;

        Member member = MemberManagerEntityFactory.createMember();
        Project project = ProjectManagerEntityFactory.createProject(member);
        Permission permission = PermissionManagerEntityFactory.createPermission(project);

        memberRepository.save(member);
        projectRepository.save(project);
        permissionRepository.save(permission);

        //when
        Optional<Permission> findPermission = permissionRepository.findByName(permissionName);
        Project findProject = findPermission.get().getProject();
        Member findMember = findProject.getMembers().get(0).getMember();

        //then
        assertThat(findPermission).isNotNull();
        assertThat(findProject).isNotNull();
        assertThat(findMember).isNotNull();

    }

    @Test
    @DisplayName("상황 1 - 접근 유저의 해당 권한 보유 여부 체크(그룹)")
    void checkMemberOwnPermission() {
        //given
        String permissionName = "must_have_permission_name";
        String memberUsername = "doe";
        Member projectOwner = MemberManagerEntityFactory.createMember("owner");
        Member member = MemberManagerEntityFactory.createMember(memberUsername);
        Project project = ProjectManagerEntityFactory.createProject(projectOwner);
        Permission permission = PermissionManagerEntityFactory.createPermission(project, permissionName);
        PermissionGroup permissionGroup =
                PermissionManagerEntityFactory.createPermissionGroup(project, projectOwner, permission);

        permissionGroup.addMember(member);

        Long savedMemberId = memberRepository.save(member).getId();
        memberRepository.save(projectOwner);
        Long savedProjectId = projectRepository.save(project).getId();
        permissionRepository.save(permission);
        permissionGroupRepository.save(permissionGroup);

        //when - 어떤 프로젝트의 해당 멤버는 해당 권한을 가지고 있는가?
        Member givenMember = memberRepository.findById(savedMemberId).orElseThrow();
        Project givenProject = projectRepository.findById(savedProjectId).orElseThrow();
        List<Permission> permissionList =
                permissionRepository.findDistinctPermissionsByProjectAndMember(givenProject, givenMember);

        //then
        assertThat(permissionList.size()).isEqualTo(1);
        assertThat(permissionList.get(0).getName()).isEqualTo(permissionName);
    }

}