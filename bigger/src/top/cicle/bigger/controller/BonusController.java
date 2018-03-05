package top.cicle.bigger.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import top.cicle.bigger.common.biggerConstants;
import top.cicle.bigger.domain.Bonus;
import top.cicle.bigger.domain.Expense;
import top.cicle.bigger.domain.User;
import top.cicle.bigger.service.biggerService;


@Controller
@RequestMapping(value="bonus")
public class BonusController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	@Qualifier("biggerService")
	private biggerService biggerservice;

	//提交报销页面
	@RequestMapping(value="addbonus",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String bonus()
	{
		return "bonus";
	}
	
	
	//提交报销
	@ResponseBody
	@RequestMapping(value="addbonus",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addBonus(@RequestParam("member") String member,@ModelAttribute Bonus bonus,HttpServletRequest request) throws JSONException
	{
		logger.info("function addBonus is running...");
		List<Integer> ids=new ArrayList();
		Integer userCount=biggerservice.User_count();
		logger.info("user total number is"+userCount);
		if(member.equals("0"))
		{
			for(int i=1;i<userCount+1;i++)
			{
				ids.add(i);
			}
		}
		else
		{
			ids.add(Integer.parseInt(member));
		}
		
		
		List<User> users=new ArrayList();
		for(int id:ids)
		{
			users.add(biggerservice.findUserById(id));  //User列表
		}
		
		JSONObject json=new JSONObject();
		try{
            for(User user:users)
            {
		        bonus.setUser(user);
			    biggerservice.addBonus(bonus);
            }
			json.put("success",true);
			json.put("message","分红单已添加成功！");
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.info(e);
			json.put("success",false);
			json.put("message","分红单添加失败！");
		}
		return json.toString();
	}
	
	@RequestMapping(value="all",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String getAllExpense()
	{
		return "bonus_show";
	}
	
	@ResponseBody
	@RequestMapping(value="getAllBonus",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String getAllBonusData() throws JSONException
	{
		JSONObject json=new JSONObject();
		JSONArray list=new JSONArray();
		List<Bonus> bonus_list=biggerservice.getAllBonusData();
		try
		{
			for(Bonus ex:bonus_list)
			{
				//组装expense数据
				String title=ex.getTitle();
				String money=ex.getMoney();
				String status=ex.getStatus();
				
				Date time=ex.getCreatetime();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String create_time=format.format(time);

				Integer user_id=ex.getUser_id();
				User user=biggerservice.findUserById(user_id);
				String name=user.getName();
				Integer id=ex.getId();
				
				JSONObject map=new JSONObject();
				map.put("id",id);
				map.put("name",name);
				map.put("title",title);
				map.put("money",money);
				map.put("status",status);
				map.put("create_time",create_time);
			
				list.add(map);
			}
			json.put("data", list);
			json.put("success",true);
			json.put("message","分红数据获取成功！");
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.info(e);
			json.put("data", "[]");
			json.put("success",false);
			json.put("message","分红数据获取失败！");
		}
		return json.toString();
	}
	
	//根据Id删除bonus记录
	@ResponseBody
	@RequestMapping(value="deletebonus",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String deleteBonusById(@RequestParam("id") Integer id) throws JSONException
	{
		JSONObject json=new JSONObject();
		try
		{
			biggerservice.deleteBonusById(id);
			json.put("success",true);
			json.put("message","删除该条分红数据成功！");
		}catch(Exception e)
		{
			logger.info(e);
			e.printStackTrace();
			json.put("success",false);
			json.put("message","删除该条分红数据失败！");
		}
		return json.toString();
	}
}
