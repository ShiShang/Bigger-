package top.cicle.bigger.controller;

import java.text.SimpleDateFormat;
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
import top.cicle.bigger.domain.Expense;
import top.cicle.bigger.domain.User;
import top.cicle.bigger.service.biggerService;



@Controller
@RequestMapping(value="expense")
public class ExpenseController {

	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	@Qualifier("biggerService")
	private biggerService biggerservice;

	//提交报销页面
	@RequestMapping(value="addexpense",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String expence()
	{
		return "expense";
	}
	
	
	//提交报销
	@ResponseBody
	@RequestMapping(value="addexpense",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addExpense(@ModelAttribute Expense expense,HttpServletRequest request) throws JSONException
	{
		logger.info("function addExpense is running...");
		JSONObject json=new JSONObject();
		User user_now=(User) request.getSession().getAttribute(biggerConstants.USER_SESSION);
		expense.setUser(user_now);
		try
		{
			biggerservice.addExpense(expense);
			json.put("success",true);
			json.put("message","报销单已添加成功！");
			
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.info(e);
			json.put("success",false);
			json.put("message","报销单添加失败！");
			
		}
		return json.toString();
	}
	
	@RequestMapping(value="all",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String getAllExpense()
	{
		return "expense_show";
	}
	
	@ResponseBody
	@RequestMapping(value="getAllExpense",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String getAllExpenseData() throws JSONException
	{
		JSONObject json=new JSONObject();
		JSONArray list=new JSONArray();
		List<Expense> expense_list=biggerservice.getAllExpenseData();
		try
		{
			for(Expense ex:expense_list)
			{
				//组装expense数据
				String title=ex.getTitle();
				String content=ex.getContent();
				String money=ex.getMoney();
				String status=ex.getStatus();
				
				Date time=ex.getCreatetime();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String create_time=format.format(time);

				String att_url=ex.getAtt_url();
				Integer user_id=ex.getUser_id();
				User user=biggerservice.findUserById(user_id);
				String name=user.getName();
				Integer id=ex.getId();
				
				JSONObject map=new JSONObject();
				map.put("id",id);
				map.put("name",name);
				map.put("title",title);
				map.put("content",content);
				map.put("money",money);
				map.put("status",status);
				map.put("create_time",create_time);
				map.put("att_url",att_url);
			
				list.add(map);
			}
			json.put("data", list);
			json.put("success",true);
			json.put("message","报销数据获取成功！");
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.info(e);
			json.put("data", "[]");
			json.put("success",false);
			json.put("message","报销数据获取失败！");
		}
		return json.toString();
	}
	
	//根据Id删除expense记录
		@ResponseBody
		@RequestMapping(value="deleteexpense",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
		public String deleteExpenseById(@RequestParam("id") Integer id) throws JSONException
		{
			JSONObject json=new JSONObject();
			try
			{
				biggerservice.deleteExpenseById(id);
				json.put("success",true);
				json.put("message","删除该条报销数据成功！");
			}catch(Exception e)
			{
				logger.info(e);
				e.printStackTrace();
				json.put("success",false);
				json.put("message","删除该条报销数据失败！");
			}
			return json.toString();
		}
	
	
}
