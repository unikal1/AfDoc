package com.afdoc.domain.memberManager.dao;

import com.afdoc.domain.memberManager.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Optional<Long> findIdByUsername(String username);
}
