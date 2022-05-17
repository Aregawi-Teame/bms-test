package com.membership.service;

import java.util.List;

import com.membership.domain.Badge;
import com.membership.domain.Member;


public interface BadgeService {
	public List<Badge> findAll();
	public Badge findById(Long id);
	public Badge save(Badge badge);
	public Badge update(Long badgeId, Badge updatedBadge);
	public void deleteById(Long id);
	public Member findBadgeMember(Long badgeId);
	public boolean isAuthorized(Long badgeId, Long locationId);
}
