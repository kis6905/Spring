package com.kis.boot.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mpg_member")
public class Member {
	
	@Id private String memberId;
	private String password;
	private Integer passwordFailCnt;
	private String nickname;
	private Integer gradeCodeGroup;
	private Integer gradeCode;
	private Date registeredDate;
	private Date modifiedDate;
	private Date lastLoginDate;

	protected Member() {}
	
	public Member(String memberId, String password, Integer passwordFailCnt, String nickname, Integer gradeCodeGroup,
			Integer gradeCode, Date registeredDate, Date modifiedDate, Date lastLoginDate) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.passwordFailCnt = passwordFailCnt;
		this.nickname = nickname;
		this.gradeCodeGroup = gradeCodeGroup;
		this.gradeCode = gradeCode;
		this.registeredDate = registeredDate;
		this.modifiedDate = modifiedDate;
		this.lastLoginDate = lastLoginDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPasswordFailCnt() {
		return passwordFailCnt;
	}

	public void setPasswordFailCnt(Integer passwordFailCnt) {
		this.passwordFailCnt = passwordFailCnt;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getGradeCodeGroup() {
		return gradeCodeGroup;
	}

	public void setGradeCodeGroup(Integer gradeCodeGroup) {
		this.gradeCodeGroup = gradeCodeGroup;
	}

	public Integer getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(Integer gradeCode) {
		this.gradeCode = gradeCode;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Override
	public String toString() {
		return String.format(
                "Member[memberId=%s, password='%s', passwordFailCnt='%d', nickname='%s', gradeCodeGroup='%d', gradeCode='%d', registeredDate='%s', modifiedDate='%s', lastLoginDate='%s']",
                memberId, password, passwordFailCnt, nickname, gradeCodeGroup, gradeCode, registeredDate, modifiedDate, lastLoginDate);
	}
}
