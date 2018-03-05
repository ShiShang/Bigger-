package top.cicle.bigger.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import top.cicle.bigger.common.biggerConstants;
import top.cicle.bigger.domain.Attachment;
import top.cicle.bigger.domain.User;
import top.cicle.bigger.service.biggerService;

@Controller
@RequestMapping(value="/att")
public class AttachmentController {

	private static Logger logger = Logger.getLogger(AttachmentController.class);
	@Autowired
	@Qualifier("biggerService")
	private biggerService biggerservice;

	//提交附件页面
	@ResponseBody
	@RequestMapping(value="addatt",method=RequestMethod.POST)
	public String addatt(@RequestParam("file") CommonsMultipartFile file,HttpSession session) throws IllegalStateException, IOException
	{
		
		JSONObject json=new JSONObject();
		//String path=session.getServletContext().getRealPath("/upload");
		String path="/usr/tomcat/attr/att";
		System.out.println(path);
		String fileName=file.getOriginalFilename();
		file.transferTo(new File(path+File.separator+fileName));
		Attachment attachment=new Attachment();
        attachment.setTitle(fileName);
        attachment.setContent(path);
        User user=(User) session.getAttribute(biggerConstants.USER_SESSION);
        attachment.setUser(user);
        try
        {
        	(biggerservice).addAttachment(attachment);
        	json.put("success", true);
        	json.put("message", "附件添加成功！");
        }catch(Exception e)
        {
        	e.printStackTrace();
        	logger.info(e);
        	json.put("success", false);
        	json.put("message", "附件添加失败！");
        }
        return json.toString();
	}

	//下载附件
	@RequestMapping(value="download")
	public ResponseEntity<byte []> download(Integer id,HttpSession session) throws IOException
	{
		Attachment target=biggerservice.selectAttachmentById(id);
		String fileName=target.getTitle();
		//String path=session.getServletContext().getRealPath("/upload");
		String path="/usr/tomcat/attr/att";

	    File file=new File(path+File.separator+fileName);
		HttpHeaders headers=new HttpHeaders();
		String downloadFileName=new String(fileName.getBytes("UTF-8"),"iso-8859-1");
		headers.setContentDispositionFormData("attachment", downloadFileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte []>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);


	}
	
	@ResponseBody
	@RequestMapping(value="getall",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String getall()
	{
		
		JSONObject json=new JSONObject();
		JSONArray list=new JSONArray();
        try
        {
        	List<Attachment> att_list=biggerservice.selectAllAttachment();
        	
        	for(Attachment att:att_list)
        	{
            	JSONObject map=new JSONObject();
            	String path=att.getContent();
            	String title=att.getTitle();
            	Integer id=att.getId();
            	Integer user_id=att.getUserId();
            	
            	Date time=att.getCreatetime();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String create_time=format.format(time);
            	User user=biggerservice.findUserById(user_id);
            	String name=user.getName();
            	
            	map.put("title",title);
            	map.put("id",id);
            	map.put("name",name);
            	map.put("path",path);
            	map.put("create_time",create_time);
            	
            	list.add(map);
        	}
        	json.put("data", list);	
        	json.put("success", true);
        	json.put("message", "附件获取成功！");
        }catch(Exception e)
        {
        	e.printStackTrace();
        	logger.info(e);
        	json.put("success", false);
        	json.put("message", "附件获取失败！");
        }
        return json.toString();
	}
	
	@RequestMapping(value="all",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String getallatt()
	{
		return "attachment_show" ;
	}
	
	//删除附件
	@ResponseBody
	@RequestMapping(value="deldowoload",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String deldownload(@RequestParam("id") Integer id)
	{
		JSONObject json=new JSONObject();
		try
		{
			biggerservice.delectAttById(id);
			json.put("success", true);
        	json.put("message", "删除附件成功！");
			
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.info(e);
			json.put("success", false);
        	json.put("message", "删除附件失败！");
			
		}
		return json.toString();
	}
}
