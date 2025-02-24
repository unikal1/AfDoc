package com.afdoc.domain.projectManager.dao;

import com.afdoc.domain.projectManager.entity.MemberProjectMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberProjectMapRepository extends JpaRepository<MemberProjectMap, Long> {

}
