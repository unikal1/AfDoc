package com.afdoc.domain.projectManager.entity;

import com.afdoc.domain.memberManager.dao.MemberRepository;
import com.afdoc.domain.memberManager.entity.Member;
import com.afdoc.domain.permissionManager.dao.PermissionRepository;
import com.afdoc.domain.projectManager.dao.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Project 엔티티 테스트")
@ActiveProfiles("test")
class ProjectTest {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private MemberRepository memberRepository;


    @Test
    @DisplayName("Project 생성 후 Permission 생성")
    void createPermission() {
        //given
        Member member = Member.builder()
                .username("username")
                .password("1111")
                .name("john")
                .imgUrl("example.url")
                .build();
        memberRepository.save(member);

        Project project = Project.builder()
                .constructor(member)
                .name("new project")
                .projectType("basic")
                .description("description of project")
                .build();

        project.addMember(member);
        Long savedId = projectRepository.save(project).getId();

        //when
        Optional<Project> findProject = projectRepository.findById(savedId);

        //then
        assertThat(findProject.isPresent()).isTrue();
        Project fProject = findProject.get();

        assertThat(fProject.getName()).isEqualTo("new project");
        assertThat(fProject.getProjectType()).isEqualTo("basic");
        assertThat(fProject.getDescription()).isEqualTo("description of project");

        assertThat(fProject.getCreatedAt()).isNotNull();
        assertThat(fProject.getJoinedMemberCnt()).isEqualTo(1L);
        assertThat(fProject.getMembers().size()).isEqualTo(1);
        assertThat(fProject.getMembers().get(0).getShownName()).isEqualTo("john");
        assertThat(fProject.getMembers().get(0).getShownImgUrl()).isEqualTo("example.url");
    }


    @Test
    @DisplayName("Permission 에 기존에 존재 했던 member 재추가")
    void reAddMember() {
        //given
        Member member = Member.builder()
                .username("username")
                .password("1111")
                .name("john")
                .imgUrl("example.url")
                .build();
        memberRepository.save(member);

        Project project = Project.builder()
                .constructor(member)
                .name("new project")
                .projectType("basic")
                .description("description of project")
                .build();

        project.addMember(member);
        Long savedId = projectRepository.save(project).getId();

        //when
        Optional<Project> findProject = projectRepository.findById(savedId);
        assertThat(findProject.isPresent()).isTrue();
        Project fProject = findProject.get();
        boolean isIncluded = fProject.addMember(member);

        //then
        assertThat(isIncluded).isFalse();
        assertThat(fProject.getName()).isEqualTo("new project");
        assertThat(fProject.getProjectType()).isEqualTo("basic");
        assertThat(fProject.getDescription()).isEqualTo("description of project");

        assertThat(fProject.getCreatedAt()).isNotNull();
        assertThat(fProject.getJoinedMemberCnt()).isEqualTo(1L);
        assertThat(fProject.getMembers().size()).isEqualTo(1);
        assertThat(fProject.getMembers().get(0).getShownName()).isEqualTo("john");
        assertThat(fProject.getMembers().get(0).getShownImgUrl()).isEqualTo("example.url");
    }

}