package com.afdoc.domain.projectManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberProjectMap extends JpaRepository<MemberProjectMap, Long> {

}
