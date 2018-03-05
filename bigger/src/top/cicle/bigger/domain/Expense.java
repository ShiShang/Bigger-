package top.cicle.bigger.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Expense implements Serializable{

	private Integer id;
	private String title;
	private String content;
	private String money;
	private User user;
	@DateTimeFormat(pattern="yyyy-mm-dd hh-mm")   //��ʱ���ʽ�Ĵ���
	private Date create_Time;
	private String status;
	private String att_url;
	private Integer user_id;

	public Expense()
	{
		super();
	}
	
	   public Integer getId()
	     {
	          return id;
	     }
	    public void setId(Integer  id)
	     {
	           this.id=id;
	     }

	    public String getTitle()
	     {
	          return title;
	     }
	    public void setTitle(String  title)
	     {
	           this.title=title;
	     }

	    public String getContent()
	     {
	          return content;
	     }
	    public void setContent(String  content)
	     {
	           this.content=content;
	     }

	    public String getMoney()
	     {
	          return money;
	     }
	    public void setMoney(String  money)
	     {
	           this.money=money;
	     }

	    public User getUser()
	     {
	          return user;
	     }
	    public void setUser(User  user)
	     {
	           this.user=user;
	     }

	    public Date getCreatetime()
	     {
	          return create_Time;
	     }
	    public void setCreatetime(Date  createTime)
	     {
	           this.create_Time=createTime;
	     }

	    public String getStatus()
	     {
	          return status;
	     }
	    public void setStatus(String  status)
	     {
	           this.status=status;
	     }

	    public String getAtt_url()
	     {
	          return att_url;
	     }
	    public void setAtt_url(String  att_url)
	     {
	           this.att_url=att_url;
	     }
	    public Integer getUser_id()
	    {
	    	return user_id;
	    }
	    public void setUser_id(Integer user_id)
	    {
	    	this.user_id=user_id;
	    }

}
