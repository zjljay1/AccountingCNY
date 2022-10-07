package com.bill_java;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BillJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillJavaApplication.class, args);
		System.out.println("启动成功：satoken配置；"+ SaManager.getConfig());
	}

}
