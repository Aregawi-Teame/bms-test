package com.membership.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.membership.domain.Location;
import com.membership.domain.Member;
import com.membership.domain.Membership;
import com.membership.domain.Plan;
import com.membership.domain.Transaction;
import com.membership.repository.MembershipRepository;

@Service
@Transactional
public class MembershipServiceImpl implements MembershipService
{
	@Autowired
	private MembershipRepository membershipRepository;
	@Override
	public List<Membership> findAll()
	{
		return membershipRepository.findAll();
	}
	@Override
	public Membership save(Membership membership)
	{
		return membershipRepository.save(membership);
	}
	@Override
	public Membership findById(Long id) 
	{
		return membershipRepository.findById(id).get();
	}
	@Override
	public List<Transaction> getTransactions(Membership membership) 
	{
		return membershipRepository.findById(membership.getId()).get().getTransactions();
	}
	@Override
	public List<Transaction> getTransactionsByMembershipId(Long membershipId) 
	{
		return membershipRepository.findById(membershipId).get().getTransactions();
	}	
	@Override
	public Plan getPlan(Membership membership) 
	{
		return membershipRepository.findById(membership.getId()).get().getPlan();
	}
	@Override
	public Plan getPlanByMembershipId(Long membershipId) 
	{
		return membershipRepository.findById(membershipId).get().getPlan();
	}
	@Override
	public Member getMember(Membership membership)
	{
		return membershipRepository.findById(membership.getId()).get().getMember();
	}
	@Override
	public Member getMemberByMembershipId(Long membershipId)
	{
		return membershipRepository.findById(membershipId).get().getMember();
	}	
	@Override
	public Location getLocation(Membership membership)
	{
		return membershipRepository.findById(membership.getId()).get().getLocation();
	}
	@Override
	public Location getLocationByMembershipId(Long membershipId)
	{
		return membershipRepository.findById(membershipId).get().getLocation();
	}
}