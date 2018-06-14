package com.fise.controller.heartbeat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fise.framework.annotation.IgnoreAuth;

/**
 * 心跳监测Controller
 */
@RestController
@RequestMapping("/heartbeat/")
public class HeartBeatController {
	
	@IgnoreAuth
	@RequestMapping("helloworld")
	public String Helloworld() {
		return "helloworld";
	}
}
