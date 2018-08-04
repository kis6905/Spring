package com.leaf.server.sample.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Level;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SampleController {
	
	@GetMapping(value = "/logging")
	public String getLogging() {
		
		log.debug("-> debug log");
		log.info("-> info log");
		log.warn("-> warn log");
		log.error("-> error log");
		
		return "success";
	}
	
	@GetMapping(value = "/log/{level}")
	public String logLevel(@PathVariable(value = "level") String level) {
		
		ch.qos.logback.classic.Logger rootLog = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.leaf.server");
		
		level = level.toLowerCase();
		if (level.equals("debug")) {
			rootLog.setLevel(Level.DEBUG);
		} else if (level.equals("info")) {
			rootLog.setLevel(Level.INFO);
		} else if (level.equals("warn")) {
			rootLog.setLevel(Level.WARN);
		} else if (level.equals("error")) {
			rootLog.setLevel(Level.ERROR);
		}
		
		return "success";
	}
	
}
