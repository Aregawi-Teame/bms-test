package com.membership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.membership.domain.Membership;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long>
{

}