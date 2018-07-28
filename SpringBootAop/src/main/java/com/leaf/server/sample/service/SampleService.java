package com.leaf.server.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.leaf.server.sample.dto.Member;

@Service
public class SampleService {
	
	public List<String> getList(int count) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			list.add("item" + (i + 1));
		}
		return list;
	}
	
	public String getOne(int id) {
		return "item " + id;
	}
	
	public Member insertMember(Member member) {
		return member;
	}
	
}
