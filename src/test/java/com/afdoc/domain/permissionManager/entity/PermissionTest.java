package com.afdoc.domain.permissionManager.entity;

import com.afdoc.domain.memberManager.dao.MemberRepository;
import com.afdoc.domain.memberManager.entity.Member;
import com.afdoc.domain.permissionManager.dao.BasePermissionRepository;
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
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    private BasePermissionRepository basePermissionRepository;


    @Test
    @DisplayName("Project 생성 후 Permission 생성")
    void createPermission() {
        //given
        String permissionName = PermissionManagerEntityFactory.permissionName;

        Member member = MemberManagerEntityFactory.createMember();
        Project project = ProjectManagerEntityFactory.createProject(member);
        BasePermission basePermission = PermissionManagerEntityFactory.createBasePermission();
        Permission permission = PermissionManagerEntityFactory.createPermission(basePermission, project);

        basePermissionRepository.save(basePermission);
        memberRepository.save(member);
        projectRepository.save(project);
        permissionRepository.save(permission);

        //when
        Optional<Permission> findPermission = permissionRepository.findByProjectAndBasePermission_Name(project, permissionName);
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
        BasePermission basePermission = PermissionManagerEntityFactory.createBasePermission();
        Permission permission = PermissionManagerEntityFactory.createPermission(basePermission, project);
        PermissionGroup permissionGroup =
                PermissionManagerEntityFactory.createPermissionGroup(project, projectOwner, permission);

        permissionGroup.addMember(member);

        Long savedMemberId = memberRepository.save(member).getId();
        memberRepository.save(projectOwner);
        Long savedProjectId = projectRepository.save(project).getId();
        basePermissionRepository.save(basePermission);
        permissionRepository.save(permission);
        permissionGroupRepository.save(permissionGroup);

        //when - 어떤 프로젝트의 해당 멤버는 해당 권한을 가지고 있는가?
        Member givenMember = memberRepository.findById(savedMemberId).orElseThrow();
        Project givenProject = projectRepository.findById(savedProjectId).orElseThrow();
        List<Permission> permissionList =
                permissionRepository.findDistinctPermissionsByProjectAndMember(givenProject, givenMember);

        //then
        assertThat(permissionList.size()).isEqualTo(1);
        assertThat(permissionList.get(0).getBasePermission().getName()).isEqualTo(permissionName);
    }

    @Test
    @DisplayName("상황 2 - 한명의 유저가 여러 그룹 소속 / 총 중복 없는 권한 불러오기")
    void checkMemberDistinctPermission() {
        //given
        Member member = MemberManagerEntityFactory.createMember("john");
        Member admin = MemberManagerEntityFactory.createMember("admin");
        Project project = ProjectManagerEntityFactory.createProject(member);

        BasePermission bp1 = PermissionManagerEntityFactory
                .createBasePermission("CHAT", "update_others");
        BasePermission bp2 = PermissionManagerEntityFactory
                .createBasePermission("CHAT", "delete_others");
        BasePermission bp3 = PermissionManagerEntityFactory
                .createBasePermission("CHAT", "create_notification");
        BasePermission bp4 = PermissionManagerEntityFactory
                .createBasePermission("CHAT", "manage_member");
        BasePermission bp5 = PermissionManagerEntityFactory
                .createBasePermission("CHAT", "invite_member");

        Permission permission1 = PermissionManagerEntityFactory
                .createPermission(bp1, project);
        Permission permission2 = PermissionManagerEntityFactory
                .createPermission(bp2, project);
        Permission permission3 = PermissionManagerEntityFactory
                .createPermission(bp3, project);
        Permission permission4 = PermissionManagerEntityFactory
                .createPermission(bp4, project);
        Permission permission5 = PermissionManagerEntityFactory
                .createPermission(bp5, project);

        PermissionGroup pGroup1 = PermissionManagerEntityFactory
                .createPermissionGroup(project, "pGroup1");
        PermissionGroup pGroup2 = PermissionManagerEntityFactory
                .createPermissionGroup(project, "pGroup2");

        memberRepository.save(admin);
        memberRepository.save(member);
        projectRepository.save(project);

        basePermissionRepository.save(bp1);
        basePermissionRepository.save(bp2);
        basePermissionRepository.save(bp3);
        basePermissionRepository.save(bp4);
        basePermissionRepository.save(bp5);

        permissionRepository.save(permission1);
        permissionRepository.save(permission2);
        permissionRepository.save(permission3);
        permissionRepository.save(permission4);
        permissionRepository.save(permission5);

        permissionGroupRepository.save(pGroup1);
        permissionGroupRepository.save(pGroup2);

        pGroup1.addMember(member);
        pGroup2.addMember(member);

        pGroup1.addPermission(permission1, admin);
        pGroup1.addPermission(permission2, admin);
        pGroup1.addPermission(permission3, admin);

        pGroup2.addPermission(permission3, admin);
        pGroup2.addPermission(permission4,admin);
        pGroup2.addPermission(permission5, admin);



        //when
        List<Permission> permissions =
                permissionRepository.findDistinctPermissionsByProjectAndMember(project, member);

        //then

        assertThat(permissions).hasSize(5);
        List<String> permissionNames = permissions.stream()
                .map(permission -> permission.getBasePermission().getName())
                .toList();

        assertThat(permissionNames)
                .containsExactlyInAnyOrder(
                        "update_others",
                        "delete_others",
                        "create_notification",
                        "manage_member",
                        "invite_member"
                );

    }

}