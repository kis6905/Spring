package com.develeaf.study.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "User")
@Table(name = "user")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userSeq;
	@Column(length = 255, nullable = true)
	private String name;
	@Column(length = 255, nullable = true)
	private String address;
	
}
