package top.cicle.bigger.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import top.cicle.bigger.domain.House;
import top.cicle.bigger.domain.Performance;
import top.cicle.bigger.domain.Performance_detail;
import top.cicle.bigger.service.biggerService;


@Controller
@RequestMapping(value="performance")
public class PerformanceController {

	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	@Qualifier("biggerService")
	private biggerService biggerservice;
	
	@RequestMapping(value="addperformance",method=RequestMethod.GET)
	public String getperformance()
	{
		return "performance";
	}
	
    @ResponseBody
	@RequestMapping(value="addpd",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String addperformance(@RequestBody  String params) throws JSONException
	{
		//System.out.println(data);
    	
    	
		Map maps = (Map)JSON.parse(params);   
		logger.info(maps.toString());
		JSONArray data_list=(JSONArray) maps.get("data");
		String year=(String) maps.get("year");
		String month=(String) maps.get("month");
		String name=(String) maps.get("name");
		String water_fee=(String) maps.get("water_fee");
		String elec_fee=(String) maps.get("elec_fee");
		
		Integer live_day_all=0;
		Integer clean_fee_all=0;
		Integer income_all=0;
		
		House house=biggerservice.findHouseByName(name);//根据名字找到房源
		JSONObject json=new JSONObject();
		
		//如果表中已经有当年当月当房源的数据，则不进行查询；
		Map data1=new HashMap();
		data1.put("year", year);
		data1.put("month", month);
		data1.put("id", house.getId());
		List<Performance> perf=biggerservice.findPerformanceByIdAndDate(data1);
		List<Performance_detail> pref_d=biggerservice.findPerformance_detailByIdAndDate(data1);
		if(perf.size()>0 || pref_d.size()>0)
		{
			json.put("success",false);
			json.put("message", "这个房源这个日期的数据已经存在，如需修改，请先删除！");
			return json.toString();
		}
		
		
		//查performance_detail 库
		try{
		for(int i=0;i<data_list.size();i++)
		{
			Map map_para=(Map) data_list.get(i); 
			String price=(String) map_para.get("price"); //价格

			if(price.equals(""))
			{
				continue;
			}
			
			String clean_fee=(String) map_para.get("clean_fee");//清洁费
			String source=(String) map_para.get("source");//预定来源
			
			String day=(String) map_para.get("day");  
			String[] day_list=day.split("-");
			String day_in=day_list[0]; //入住时间
			String day_out=day_list[1];//退房时间
			String day_live=Integer.parseInt(day_out)-Integer.parseInt(day_in)+"";//入住天数
			
			//计算performance数据
			live_day_all+=Integer.parseInt(day_live);
			clean_fee_all+=Integer.parseInt(clean_fee);
			income_all+=Integer.parseInt(price);
			
			//组装Performance_detail对象
			Performance_detail pd=new Performance_detail();
			pd.setYear(year);  //年份
			pd.setMonth(month);  //月份
			pd.setDay_in(day_in);  //入住日期
			pd.setDay_out(day_out);  //退房日期
			pd.setDay_live(day_live);  //入住天数
			pd.setPrice(price);   //订单收入
			pd.setClean_fee(clean_fee);  //清洁费
			pd.setSource(source);   //订单来源
			pd.setHouse(house);   //房源名称
		
			
			//插库Performance_detail
			biggerservice.addPerformance_detail(pd);
			
		}
		try{
		Integer fee_all=Integer.parseInt(water_fee)+Integer.parseInt(elec_fee)+clean_fee_all;
	    Integer profit=income_all-fee_all-house.getCost(); 
	    String[] array={"1","3","5","7","8","10","12"};
	    List<String> mon_list=Arrays.asList(array);
	    String days="";
		if(mon_list.contains(month))
		{
			 days="31";
		}else
		{
			 days="30"; 
		}
	    
		//组装peformance数据
		Performance pf=new Performance();
		pf.setYear(year);                //年份
		pf.setMonth(month);              //月份
		pf.setCost(house.getCost()+"");  //房源成本
		pf.setFee(fee_all+"");           //总费用
		pf.setWater_fee(water_fee);      //水费
		pf.setElec_fee(elec_fee);         //电费
		pf.setClean_fee(clean_fee_all+"");//清洁费
		pf.setDays(days);                 //当月天数
		pf.setLive_days(live_day_all+"");      //入住天数
		pf.setIncome(income_all+"");      //总收入
		pf.setProfit(profit+"");          //利润
		pf.setHouse(house);               //房源名称
		
		//插Performance库
		biggerservice.addPerformance(pf);
		
		
		}catch(Exception e)
		{
			json.put("success",false);
			json.put("message", "Performance表添加失败！");
		}
		
		json.put("success",true);
		json.put("message", "业绩添加成功！");
		}catch(Exception e)
		{
			logger.info(e);
			e.printStackTrace();
			json.put("success",false);
			json.put("message", "业绩添加失败！");
		}
	    
		return json.toString();
	}
    
    //查询业绩接口
    @RequestMapping(value="{house_id}/{year}/{month}",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getpdlist(@PathVariable Integer house_id,@PathVariable String year,@PathVariable String month)
    {
    	logger.info("getpdList is running");
    	System.out.println("getpdList is running");
    	House house=biggerservice.findHouseById(house_id);
    	String name=house.getName();
    	
    	Map<String,Object> params=new HashMap();
    	params.put("id", house_id);
    	params.put("year", year);
    	params.put("month", month);
    	logger.info(params.toString());
    	
    	List<Performance_detail> pd_list=biggerservice.findPerformance_detailByIdAndDate(params);
    	List<Performance> pf_list=biggerservice.findPerformanceByIdAndDate(params);
    	Performance pf=pf_list.get(0);
    	
    	JSONObject json=new JSONObject();
    	JSONArray json_list=new JSONArray();
    	for (Performance_detail pd:pd_list)
    	{
    		JSONObject map1=new JSONObject();
    		map1.put("date",pd.getDay_in()+"-"+pd.getDay_out());
    		map1.put("price",pd.getPrice());
    		map1.put("clean_fee",pd.getClean_fee());
    		map1.put("source",pd.getSource());
    		json_list.add(map1);
    		
    	}
    	json.put("year",year);
    	json.put("month",month);
    	json.put("water_fee",pf.getWater_fee());
    	json.put("elec_fee",pf.getElec_fee());
    	json.put("name",name);
    	json.put("data",json_list);
    	
    	return json.toString();

    }
    
    //Performance_detail 修改&展示 页面
    @RequestMapping(value="table")
    public String modifyPerformance()
    {
    	return "modifyperformance";
    }
    
    //联动删除Performance_detail 和Performance记录接口
    @ResponseBody
	@RequestMapping(value="deletepd",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String deleteperformance(@RequestBody  String params) throws JSONException
	{
		//System.out.println(data);
		
		Map maps = (Map)JSON.parse(params);   
		logger.info(maps.toString());
		String year=(String) maps.get("year");
		String month=(String) maps.get("month");
		String name=(String) maps.get("name");
		House house=biggerservice.findHouseByName(name);//根据名字找到房源
		
		Map params_delete=new HashMap();
		params_delete.put("house_id", house.getId());
		params_delete.put("year", year);
		params_delete.put("month",month);
		
		//删除Performance_detail和performance数据
		JSONObject json=new JSONObject();
		try
		{
			biggerservice.deletePerformance_detailByIdAndDate(params_delete);
			biggerservice.deletePerformanceByIdAndDate(params_delete);
			json.put("message","删除成功！");
			json.put("status",true);
			
		}catch(Exception e)
		{
			logger.info(e);
			e.printStackTrace();
			json.put("message","删除失败！");
			json.put("status",false);
		}
		
		return json.toString();

	}
	
    //展示全部的业绩
    @RequestMapping(value="all",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String  getAllPerformance()
    {
    	return "pf_show";
    }
    
    //查询全部业绩接口
    @ResponseBody
    @RequestMapping(value="getAllPerformance",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String getAllPerformanceData()
    {
    	logger.info("function getAllPerformanceData is running...");
    	List<Performance> pf_list=biggerservice.getAllPerformanceData();
    	JSONArray list=new JSONArray();
    	JSONObject json=new JSONObject();
    	
    	try{
         	for(Performance pf : pf_list)
    	    {
    	  	  //组装Performance数据
    		  Integer house_id=pf.getHouse_id();
    		  House house=biggerservice.findHouseById(house_id);
    		  
    		  String name=house.getName();
    		  String year=pf.getYear();
    		  String month=pf.getMonth();
    		  String date=pf.getYear()+"年"+pf.getMonth()+"月";
    		  String income=pf.getIncome();
    		  String fee=pf.getFee();
    		  String profit=pf.getProfit();
    		  String days=pf.getLive_days();
    		
    		  JSONObject map=new JSONObject();
    		  map.put("name", name);
    		  map.put("date", date);
    		  map.put("income", income);
    		  map.put("fee", fee);
    		  map.put("profit", profit);
    		  map.put("days", days);
    		  map.put("house_id", house_id);
    		  map.put("year", year);
    		  map.put("month", month);
    		
    		  list.add(map);
    	    }
    	    json.put("data", list);
    	    json.put("success", true);
    	    json.put("message", "业绩数据获取成功！");
    	    
    }catch(Exception e)
    {
    	logger.info(e);
    	e.printStackTrace();
    	json.put("data","[]");
	    json.put("success", false);
	    json.put("message", "业绩数据获取失败！");
    }
    	return json.toString();
    }
    
    
    
    
    
}
