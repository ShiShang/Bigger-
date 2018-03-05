package top.cicle.bigger.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import top.cicle.bigger.domain.House;
import top.cicle.bigger.domain.Performance;
import top.cicle.bigger.domain.User;
import top.cicle.bigger.service.biggerService;


@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	@Qualifier("biggerService")
	private biggerService biggerservice;
	
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String getHome()
	{
		  
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value="/home/getprofit",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String getProfit()
	{
		//组装查询时间-利润-房源参数
		  List<Map<String,Object>> data=new LinkedList();
		  Calendar c = Calendar.getInstance();
		  JSONObject json=new JSONObject();
		  
		  try{
	         for(int i=0;i<1;i++)
		         {
			       c.add(Calendar.MONTH, -1);
			       SimpleDateFormat format =  new SimpleDateFormat("M");
			       SimpleDateFormat format1 =  new SimpleDateFormat("yyyy");
			       String month = format.format(c.getTime());
			       String year = format1.format(c.getTime());
			       
			        for(int house_id=1;house_id<5;house_id++)
			       {
			           Map<String,Object> params=new HashMap();
			           params.put("year",year);
			           params.put("month", month);
			           params.put("id", house_id);
			           data.add(params);
			     
			       }
		  }
	         
		  //根据参数查找利润
		  JSONArray list=new JSONArray();
		  for(Map<String,Object> param:data)
		  {
			 List<Performance> list_pf=biggerservice.findPerformanceByIdAndDate(param);
			 //System.out.println(param.toString());
			 if(list_pf.size()==0)
			 {
				 continue;
			 }
             Performance pf=list_pf.get(0);
             House house=biggerservice.findHouseById((Integer)param.get("id"));
             Integer id=house.getId();
             User user=biggerservice.findUserById(id);
             String user_name=user.getName();
             Double percet=(Double.parseDouble(pf.getLive_days())/Double.parseDouble(pf.getDays()))*100;
             BigDecimal a=new BigDecimal(percet);
             
             
             String date=pf.getYear()+" 年  "+pf.getMonth()+" 月  ";
             String name=house.getName();
             Integer profit=Integer.parseInt(pf.getProfit());		       
             
             JSONObject tmp=new JSONObject();
             tmp.put("date", date);
             tmp.put("name", name);
             tmp.put("profit", profit);
             tmp.put("user", user_name);
             tmp.put("percet", a.setScale(2,2));
     
             list.add(tmp);
		     json.put("data",list);
		     json.put("success", true);
		     json.put("message", "获取利润数据成功！");
		  }
		  }catch(Exception e)
		  {
			  e.printStackTrace();
			  logger.info(e);
			  json.put("data","[]");
			  json.put("success", true);
			  json.put("message", "获取利润数据失败！");

		  }
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/home/getpercent",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String getPercent()
	{
		//组装查询时间-利润-房源参数
		  List<Map<String,Object>> data1=new LinkedList();
		  Calendar c = Calendar.getInstance();
		  JSONObject json=new JSONObject();
		  try{
			  c.add(Calendar.MONTH, -1);
			  SimpleDateFormat format =  new SimpleDateFormat("M");
			  SimpleDateFormat format1 =  new SimpleDateFormat("yyyy");
			  String month = format.format(c.getTime());
			  String year = format1.format(c.getTime());
			  for(int house_id=1;house_id<5;house_id++)
			  {
				  Map<String,Object> params=new HashMap();
			      params.put("year",year);
			      params.put("month", month);
			      params.put("id", house_id);
			      data1.add(params); 
			  }
			  

              //查找总订单数
               //c.add(Calendar.MONTH, -1);
		       SimpleDateFormat format2 =  new SimpleDateFormat("M");
		       SimpleDateFormat format3 =  new SimpleDateFormat("yyyy");
		       String month1 = format.format(c.getTime());
		       String year1 = format1.format(c.getTime());
		       Map param_order=new HashMap();
		       param_order.put("year", year1);
		       param_order.put("month", month1);
		       String date1="("+year1+"-"+month1+")";
		       Integer order=biggerservice.getAllOrderByDate(param_order);
		       //System.out.println("Order:"+order);
			  
		  //System.out.println(data1.toString());
		  Integer income=0;
		  Integer water_fee=0;
		  Integer elec_fee=0;
		  Integer cost=0;
		  Integer clean_fee=0;
		  Integer profit=0;
 
		  for(Map<String,Object> param:data1)
		  {
			 List<Performance> list_pf=biggerservice.findPerformanceByIdAndDate(param);
			 if(list_pf.size()==0)
			 {
				 continue;
			 }
             Performance pf=list_pf.get(0);
             
             income+=Integer.parseInt(pf.getIncome());
             water_fee+=Integer.parseInt(pf.getWater_fee());
             elec_fee+=Integer.parseInt(pf.getElec_fee());
             cost+=Integer.parseInt(pf.getCost());
             clean_fee+=Integer.parseInt(pf.getClean_fee());
             profit+=Integer.parseInt(pf.getProfit()); 
		  }
		     //json.put("income", income);
		     JSONObject json_data=new JSONObject();
		     json_data.put("总水费", water_fee); 
		     json_data.put("总电费", elec_fee);
		     json_data.put("总清洁费", clean_fee);
		     json_data.put("总租赁费", cost);
		     json_data.put("总利润", profit);
		     
		     json.put("data", json_data);
		     json.put("profit", profit);
		     json.put("income", income);
		     json.put("order", order);
		     json.put("date1", date1);
		     

		  }catch(Exception e)
		  {
			  e.printStackTrace();
			  logger.info(e);
			  json.put("data","[]");
			  json.put("success", true);
			  json.put("message", "获取数据失败！");
		  }
		return json.toString();
	}
	

	@ResponseBody
	@RequestMapping(value="/home/getdata",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String getData()
	{
		  //组装查询参数
		  List<Map<String,Object>> data=new LinkedList();
		  Calendar c = Calendar.getInstance();
		  JSONObject json=new JSONObject();
		  
		  try{
	         for(int i=0;i<8;i++)
		         {
			       c.add(Calendar.MONTH, -1);
			       SimpleDateFormat format =  new SimpleDateFormat("M");
			       SimpleDateFormat format1 =  new SimpleDateFormat("yyyy");
			       String month = format.format(c.getTime());
			       String year = format1.format(c.getTime());

			       Map<String,Object> params=new HashMap();
			       params.put("year",year);
			       params.put("month", month);
			       data.add(params);
			       }
	          //日期倒序
	          Collections.reverse(data);
	          //根据参数查找利润
			  JSONArray list=new JSONArray();
			  for(Map<String,Object> param:data)
			  {
				 Integer profit=biggerservice.getMonthProfitByDate(param);
				 //System.out.println(param.toString());
				 if(profit==null)
				 {
					 continue;
				 }
	             String date=param.get("year")+"-"+param.get("month");
	             JSONObject tmp=new JSONObject();
	             tmp.put("date", date);
	             tmp.put("profit", profit);
	     
	             list.add(tmp);
			     json.put("data",list);
			     json.put("success", true);
			     json.put("message", "获取利润数据成功！");
			  }
			  }catch(Exception e)
			  {
				  e.printStackTrace();
				  logger.info(e);
				  json.put("data","[]");
				  json.put("success", true);
				  json.put("message", "获取利润数据失败！");

			  }
			return json.toString();

	}
}
