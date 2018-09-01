package com.leaf.server.sample.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.leaf.server.sample.dto.Member;
import com.leaf.server.sample.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Async
@Service
public class SampleService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@PostConstruct
	private void init() {
		log.info("===========================================================");
		log.info("== init");
		memberRepository.saveAll(Arrays.asList(
				Member.builder().id(1).name("iskwon").age(30).build(),
				Member.builder().id(2).name("test01").age(25).build(),
				Member.builder().id(3).name("test02").age(22).build(),
				Member.builder().id(4).name("test03").age(35).build(),
				Member.builder().id(5).name("test04").age(40).build()));
		log.info("== done init");
		log.info("===========================================================");
	}
	
	public CompletableFuture<List<Member>> findAll() {
		log.info("~~ service.findAll()");
		List<Member> list = memberRepository.findAll();
		
		list.stream().forEach(System.out::println);
		return CompletableFuture.completedFuture(list);
	}
	
	public CompletableFuture<Member> findByOne(Long id) {
		log.info("~~ service.findByOne()");
		return CompletableFuture.completedFuture(memberRepository.findById(id).orElse(null));
	}

	public CompletableFuture<Member> add(Member member) {
		log.info("~~ service.add()");
		return CompletableFuture.completedFuture(memberRepository.save(member));
	}

	public CompletableFuture<Member> modify(Member member) {
		log.info("~~ service.modify()");
		return CompletableFuture.completedFuture(
					memberRepository
						.findById(member.getId())
						.orElseThrow(RuntimeException::new)
				).thenApply(m -> {
					log.info("~~ found member = {}", m.toString());
					m.setName(member.getName());
					m.setAge(member.getAge());
					return m;
				});
	}

	public void remove(Long id) {
		memberRepository.deleteById(id);
	}
	
}
