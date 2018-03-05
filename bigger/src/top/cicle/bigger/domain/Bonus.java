package top.cicle.bigger.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Bonus implements Serializable{

	private Integer id;
	@DateTimeFormat(pattern="yyyy-mm-dd")   //��ʱ���ʽ�Ĵ���
	private Date create_Time;
	@DateTimeFormat(pattern="yyyy-mm-dd")   //��ʱ���ʽ�Ĵ���
	private Date modify_Time;
	private String money;
	private String status;
	private User user;
	private String title;
	private Integer user_id;
	
	public Bonus()
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

    public Date getCreatetime()
    {
         return create_Time;
    }
    public void setCreatetime(Date  createTime)
    {
          this.create_Time=createTime;
    }

    public Date getModifytime()
    {
         return modify_Time;
    }
    public void setModifytime(Date  modifyTime)
    {
          this.modify_Time=modifyTime;
    }

    public String getMoney()
    {
         return money;
    }
    public void setMoney(String  money)
    {
          this.money=money;
    }

    public String getStatus()
    {
         return status;
    }
    public void setStatus(String  status)
    {
          this.status=status;
    }

    public User getUser()
    {
         return user;
    }
    public void setUser(User  user)
    {
          this.user=user;
    }
    public String getTitle()
    {
         return title;
    }
    public void setTitle(String  title)
    {
          this.title=title;
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
