package com.develeaf.study.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "Team")
@Table(name = "team")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teamSeq;
	@Column(length = 255, nullable = true)
	private String name;
	
//	@OneToMany(mappedBy = "team")
//	@JsonManagedReference
//	private List<UserEntity> userList;
	
}
