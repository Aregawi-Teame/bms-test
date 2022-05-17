package com.membership.domain;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "members")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="member_id")
	private Long id;
	@Column(name ="first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String email;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "member_badge", joinColumns = {@JoinColumn(name="member_id")},
									inverseJoinColumns = {@JoinColumn(name="badge_id")})	
	private Set<Badge> badges;

	@NotNull
	@OneToMany()
	@JoinTable(name = "member_role", joinColumns = {@JoinColumn(name="member_id")},
									inverseJoinColumns = {@JoinColumn(name="role_id")})
	private Set<Role> roles;
	
	@OneToMany
	@JoinTable(name = "member_membership", joinColumns = {@JoinColumn(name="member_id")},
									inverseJoinColumns = {@JoinColumn(name="membership_id")})
	private Set<Membership> memberships;
	
	@OneToMany()
	@JoinTable(name = "member_transaction", joinColumns = {@JoinColumn(name="member_id")},
			inverseJoinColumns = {@JoinColumn(name="transaction_id")})
	private Set<Transaction> transactions;
	
	public Member(String firstName, String lastName, String email, Set<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
	public void addBadge(Badge badge) {
		this.badges.add(badge);
		badge.setMember(this);
	}
	
	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
	
	public void addMemebrship(Membership membership) {
		this.memberships.add(membership);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName);
	}
	
}
