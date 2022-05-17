package com.membership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.membership.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
