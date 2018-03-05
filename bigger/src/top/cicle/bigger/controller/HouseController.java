package top.cicle.bigger.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import top.cicle.bigger.domain.House;
import top.cicle.bigger.domain.User;
import top.cicle.bigger.service.biggerService;


@Controller
@RequestMapping(value="/house")
public class HouseController {

	
	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	@Qualifier("biggerService")
	private biggerService biggerservice;

	//登陆页面
	@RequestMapping(value="addhouse",method=RequestMethod.GET)
	public String gethouse()
	{
		return "house";
	}
	
	//添加房源
	@ResponseBody
	@RequestMapping(value="addhouse",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addHouse(@RequestParam("member") String member, @ModelAttribute House house) throws JSONException
	{
		JSONObject json=new JSONObject();
		User user=new User();
		user.setName(member);
		Map<String,Object> params=new HashMap();
		params.put("user", user);
		logger.info("start working user...");
		User user_now=biggerservice.selectUserwithName(params).get(0);
		if(user_now==null)
		{
			json.put("status", false);
			json.put("message", "你输入的管理者姓名不存在！");
		}
		else
		{
			try
			{
				house.setUser(user_now);
				biggerservice.addHouse(house);
				json.put("status", true);
				json.put("message", "添加房源成功！");
				
			}catch(Exception e)
			{
				logger.info(e);
				json.put("status", false);
				json.put("message", "添加房源失败！");
			}
		}
		return json.toString();
	}
	
	//房源列表
	@ResponseBody
	@RequestMapping(value="houselist",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String getAllHouse() throws JSONException
	{
		//System.out.println("here");
		logger.info("houselist interface is running!");
		JSONObject json=new JSONObject();
		List<House> house_list=biggerservice.getAllHouse();
		JSONArray list=new JSONArray();
		try
		{
			for(House house:house_list)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD hh:mm");
				String name=house.getName();
				Date start_time=house.getStart_time();
				Date end_time=house.getEnd_time();
				//System.out.println(start_time);
				//String start =sdf.format(start_time);
				//System.out.println(start);

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String start=format.format(start_time);
				String end =format.format(end_time);
				
				String status=house.getStatus();
				Integer cost=house.getCost();
				Integer id=house.getUserId();
				User user=biggerservice.findUserById(id);
				String user_name=user.getName();
				Integer house_id=house.getId();
				
				JSONObject map=new JSONObject();
				map.put("name",name);
				map.put("start",start);
				map.put("end",end);
				map.put("status",status);
				map.put("cost",cost);
				map.put("user_name",user_name);
				map.put("house_id",house_id);
				
				list.add(map);		
			}
			
			json.put("data", list);
			json.put("success", true);
			json.put("message", "房源数据获取成功！");
		}catch(Exception e)
			{
				logger.info(e);
				e.printStackTrace();
				json.put("status", false);
				json.put("message", "房源数据获取失败！");
			}

		return json.toString();
	}
	
	@RequestMapping(value="all",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String getHouse()
	{
		return "house_show";
	}
	
	@RequestMapping(value="table",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String getHouse_detail()
	{
		return "house_edit";
	}
	
	@ResponseBody
	@RequestMapping(value="detail",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addHouse(@RequestParam("id") Integer id) throws JSONException
	{
		JSONObject json=new JSONObject();
		House house=biggerservice.findHouseById(id);
		
		try
		{
			JSONObject data=new JSONObject();
			data.put("name", house.getName());
			data.put("start_time", house.getStart_time());
			data.put("end_time", house.getEnd_time());
			data.put("cost", house.getCost());
			data.put("status", house.getStatus());
			data.put("remark", house.getRemark());
			User user=biggerservice.findUserById(house.getUserId());
			data.put("controller", user.getName());	
			json.put("data", data);
			json.put("success", true);
			json.put("message", "房源数据获取成功！");
			
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.info(e);
			json.put("success", false);
			json.put("message", "房源数据获取失败！");
		}
		return json.toString();
		
	}
}
