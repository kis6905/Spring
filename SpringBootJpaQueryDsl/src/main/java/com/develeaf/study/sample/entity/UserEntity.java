package com.develeaf.study.sample.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "User")
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userSeq;
	@Column(length = 255, nullable = true)
	private String name;
	@Column(length = 255, nullable = true)
	private String address;
	
	@ManyToOne
	@JoinColumn(name = "team_seq")
	@JsonBackReference
	private TeamEntity team;
	
	@ManyToMany
	@JoinTable(name = "user_role",
			   joinColumns = @JoinColumn(name = "user_seq"),
			   inverseJoinColumns = @JoinColumn(name = "role_seq"))
	private List<RoleEntity> roleList;
	
}
