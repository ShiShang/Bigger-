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

import top.cicle.bigger.domain.Bonus;
import top.cicle.bigger.domain.Notice;
import top.cicle.bigger.domain.User;
import top.cicle.bigger.service.biggerService;



@Controller
@RequestMapping(value="notice")
public class NoticeController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	@Qualifier("biggerService")
	private biggerService biggerservice;
	
	@RequestMapping(value="addnotice",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String getnotice()
	{
		return "notice";
	}
	
	@ResponseBody
	@RequestMapping(value="addnotice",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addnotice(@ModelAttribute Notice notice,@RequestParam("member") String member) throws JSONException
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
				notice.setUser(user_now);
				biggerservice.addNotice(notice);
				json.put("status", true);
				json.put("message", "公告发布成功！");
				
			}catch(Exception e)
			{
				logger.info(e);
				json.put("status", false);
				json.put("message", "公告发布失败！");
			}
		}
		return json.toString();
	}

	@RequestMapping(value="all",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String getAllNotice()
	{
		return "notice_show";
	}
	
	@ResponseBody
	@RequestMapping(value="getAllNotice",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String getAllNoticeData() throws JSONException
	{
		JSONObject json=new JSONObject();
		JSONArray list=new JSONArray();
		List<Notice> notice_list=biggerservice.getAllNoticeData();
		try
		{
			for(Notice ex:notice_list)
			{
				//组装expense数据
				String title=ex.getTitle();
				String content=ex.getContent();
				if(content.length()>=30)
				{
					content=content.substring(0, 30)+"......";
				}
				
				
				String level=ex.getLevel();
				
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
				map.put("content",content);
				map.put("level",level);
				map.put("create_time",create_time);
			
				list.add(map);
			}
			json.put("data", list);
			json.put("success",true);
			json.put("message","公告数据获取成功！");
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.info(e);
			json.put("data", "[]");
			json.put("success",false);
			json.put("message","公告数据获取失败！");
		}
		return json.toString();
	}
	
	@RequestMapping(value="detail",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String getAllNoticeDetail()
	{
		return "notice_detail";
	}
	
	@ResponseBody
	@RequestMapping(value="detail/{id}",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String getNoticeById(@RequestParam("id") Integer id ) throws JSONException
	{
	    JSONObject json=new JSONObject();
	    try
	    {
	    	Notice notice=biggerservice.getNoticeById(id);
	    	
	    	//组装Notice详情数据
	    	String title=notice.getTitle();
	    	String content=notice.getContent();
	    	String level=notice.getLevel();
	    	
	    	Date time=notice.getCreatetime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String create_time=format.format(time);
			
			Integer user_id=notice.getUser_id();
			User user=biggerservice.findUserById(user_id);
			String name=user.getName();
			
			json.put("success",true);
			json.put("name",name);
			json.put("title",title);
			json.put("content",content);
			json.put("level",level);
			json.put("create_time",create_time);			
	    }catch(Exception e)
	    {
	    	logger.info(e);
	    	json.put("success",false);
			json.put("message","公告详情获取失败！");
	    }
	    return json.toString();
	}
	
	//根据Id删除notice记录
		@ResponseBody
		@RequestMapping(value="deletenotice",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
		public String deleteNoticeById(@RequestParam("id") Integer id) throws JSONException
		{
			JSONObject json=new JSONObject();
			try
			{
				biggerservice.deleteNoticeById(id);
				json.put("success",true);
				json.put("message","删除该条公告数据成功！");
			}catch(Exception e)
			{
				logger.info(e);
				e.printStackTrace();
				json.put("success",false);
				json.put("message","删除该条公告数据失败！");
			}
			return json.toString();
		}
	
}
