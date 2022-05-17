package com.membership.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.membership.domain.Badge;
import com.membership.domain.Location;
import com.membership.domain.LocationTypeEnum;
import com.membership.domain.Member;
import com.membership.domain.Membership;
import com.membership.domain.Plan;
import com.membership.domain.Role;
import com.membership.domain.TimeSlot;
import com.membership.domain.Transaction;
import com.membership.repository.BadgeRepository;
import com.membership.repository.TimeSlotRepository;

@Service
@Transactional
public class BadgeServiceImpl implements BadgeService {

	@Autowired
	private BadgeRepository badgeRepository;
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	@Autowired
	private MemberService memberService;

	@Override
	public List<Badge> findAll() {
		return badgeRepository.findAll();
	}

	@Override
	public Badge findById(Long id) {
		return badgeRepository.findById(id).get();
	}

	@Override
	public Badge save(Badge badge) {
		return badgeRepository.save(badge);
	}

	@Override
	public Badge update(Long badgeId, Badge updatedBadge) {
		Badge oldBadge = findById(badgeId);
		if (updatedBadge.getIssueDate() != null)
			oldBadge.setIssueDate(updatedBadge.getIssueDate());
		if (updatedBadge.getExpirationDate() != null)
			oldBadge.setExpirationDate(updatedBadge.getExpirationDate());
		oldBadge.setActive(updatedBadge.isActive());

		save(oldBadge);

		return oldBadge;
	}

	@Override
	public void deleteById(Long id) {
		badgeRepository.deleteById(id);
	}

	@Override
	public Member findBadgeMember(Long badgeId) {
		return findById(badgeId).getMember();
	}

	@Override
	public boolean isAuthorized(Long badgeId, Long locationId) {
		Badge badge = findById(badgeId);
		Member member = badge.getMember();
		Membership membership = member.getMemberships().stream()
				.filter(membersh -> membersh.getLocation().getLocationId() == locationId)
				.findFirst()
				.get();

		if (badge == null || member == null || membership == null)
			return false; // Not authorized
		if (!badge.isActive() || badge.getExpirationDate().isBefore(LocalDate.now()))
			return false; // Not authorized
		if (membership.getEndDate().isBefore(LocalDate.now()))
			return false; // Membersip is expired

		Plan plan = membership.getPlan();
		Location location = membership.getLocation();
		Integer dayOfTheWeek = LocalDate.now().getDayOfWeek().getValue();
		List<TimeSlot> dayTimeSlot = location.getTimeSlots()
				.stream()
				.filter(s -> s.getDayOfWeek().valueOfTheDay() == dayOfTheWeek)
				.toList();

		LocalTime currentTime = LocalTime.now();
		TimeSlot timeSlot = dayTimeSlot.stream()
				.filter(s -> s.getStartTime().isAfter(currentTime) && s.getEndTime().isBefore(currentTime)).findFirst()
				.get();
		if (timeSlot == null)
			return false; // this means out of time or not opened yet;

		if (!allowedRoleFoundInMember(member, plan))
			return false;

		if (plan.isLimited()) {
			long successfullTransactionsCount = membership.getTransactions().stream()
					.filter(t -> t.getDateTime().getMonthValue() == LocalDate.now().getMonthValue())
					.filter(t -> t.getDateTime().getYear() == LocalDate.now().getYear()).filter(t -> t.isSuccessful())
					.count();

			if (plan.getQuota() <= successfullTransactionsCount)
				return false;// quota overflowed
		}

		Transaction transaction = new Transaction();
		transaction.setSuccessful(true);
		transaction.setDateTime(LocalDate.now());
		membership.addTransaction(transaction);
		member.addTransaction(transaction);
		memberService.save(member);

		return true;
	}

	public boolean allowedRoleFoundInMember(Member member, Plan plan) {
		Set<Role> allowedRoles = plan.getRoles();
		for (Role role : allowedRoles)
			if (member.getRoles().contains(role))
				return true;
		return false;
	}
}
