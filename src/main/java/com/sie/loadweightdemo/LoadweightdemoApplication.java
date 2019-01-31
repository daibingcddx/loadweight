package com.sie.loadweightdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sie.controller.SatWeightApplet;
import com.sie.entity.ReturnData;

import net.sf.json.JSONObject;

@RequestMapping
@SpringBootApplication
public class LoadweightdemoApplication extends SpringBootServletInitializer{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LoadweightdemoApplication.class);
    }
	
	
	@RequestMapping("/loadweight")
	@ResponseBody
	public String loadweight(){
		ReturnData returnData = new ReturnData();
		String weight="";
		try {
			 SatWeightApplet satWeightApplet = new SatWeightApplet();
	         weight = satWeightApplet.loadWeight();
	         returnData.setKey("SUCC");
	         returnData.setValue(weight);
		} catch (Exception e) {
			e.printStackTrace();
			returnData.setKey("ERR");
	        returnData.setValue(e.getMessage());
		}
		JSONObject jsonObject = JSONObject.fromObject(returnData);
		return "success_jsonpCallback("+ jsonObject.toString() +");";
	}
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "hello";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LoadweightdemoApplication.class, args);
	}
}




