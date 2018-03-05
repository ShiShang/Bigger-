package top.cicle.bigger.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Notice implements Serializable{

	private Integer id;
	private String title;
	private String content;
	@DateTimeFormat(pattern="yyyy-mm-dd")   //设置日期格式
	private Date create_Time;
	private User user;
	private String level;
	private Integer user_id;
	
	public Notice()
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

    public Date getCreatetime()
    {
         return create_Time;
    }
    public void setCreatetime(Date  createTime)
    {
          this.create_Time=createTime;
    }

    public User getUser()
    {
         return user;
    }
    public void setUser(User  user)
    {
          this.user=user;
    }
    public String getLevel()
    {
         return level;
    }
    public void setLevel(String  level)
    {
          this.level=level;
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
