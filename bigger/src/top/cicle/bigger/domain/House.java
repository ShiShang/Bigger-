package top.cicle.bigger.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class House implements Serializable{

	private Integer id;
	private String name;
	@DateTimeFormat(pattern="yyyy-mm-dd")   //��ʱ���ʽ�Ĵ���
	private Date start_time;
	@DateTimeFormat(pattern="yyyy-mm-dd")   //��ʱ���ʽ�Ĵ���
	private Date end_time;
	private User user;
	private Integer cost;
	private String remark;
	private String status;
	private Integer user_id;
	
	public House()
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

   public String getName()
    {
         return name;
    }
   public void setName(String  name)
    {
          this.name=name;
    }

   public Date getStart_time()
    {
         return start_time;
    }
   public void setStart_time(Date  start_time)
    {
          this.start_time=start_time;
    }

   public Date getEnd_time()
    {
         return end_time;
    }
   public void setEnd_time(Date  end_time)
    {
          this.end_time=end_time;
    }

   public User getUser()
    {
         return user;
    }
   public void setUser(User  user)
    {
          this.user=user;
    }
   public Integer getCost()
   {
	   return cost;
   }
   public void setCost(Integer cost)
   {
	   this.cost=cost;
   }
   public String getRemark()
   {
        return remark;
   }
   public void setRemark(String remark)
   {
         this.remark=remark;
   }
   public String getStatus()
   {
        return status;
   }
   public void setStatus(String  status)
   {
        this.status=status;
   }
   public Integer getUserId()
   {
        return user_id;
   }
  public void setUserId(Integer  user_id)
   {
         this.user_id=user_id;
   }
}
