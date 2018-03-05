package top.cicle.bigger.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User implements Serializable {

	private Integer id;
	private String name;
	private String tel;
	private String email;
	private String position;
	@DateTimeFormat(pattern="yyyy-mm-dd")   //��ʱ���ʽ�Ĵ���
	private Date in_time;
	private String sex;	
	private String pic_url;
	private String profile;
	private String remark;
	private String password;
	private Integer user_id;
	
	public User()
	{
		super();
	}
	
	//javaBean
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

    public String getTel()
    {
         return tel;
    }
    public void setTel(String  tel)
    {
          this.tel=tel;
    }

    public String getEmail()
    {
         return email;
    }
    public void setEmail(String  email)
    {
          this.email=email;
    }

    public String getPosition()
    {
         return position;
    }
    public void setPosition(String  position)
    {
          this.position=position;
    }

    public Date getInTime()
    {
         return in_time;
    }
    public void setInTime(Date  in_time)
    {
          this.in_time=in_time;
    }

    public String getSex()
    {
         return sex;
    }
    public void setSex(String  sex)
    {
          this.sex=sex;
    }

    public String getPic_url()
    {
         return pic_url;
    }
    public void setPic_url(String  pic_url)
    {
          this.pic_url=pic_url;
    }

    public String getProfile()
    {
         return profile;
    }
    public void setProfile(String  profile)
    {
          this.profile=profile;
    }

    public String getRemark()
    {
         return remark;
    }
    public void setRemark(String  remark)
    {
          this.remark=remark;
    }
    
    public String getPassword()
    {
    	return password;
    }
    public void setPassword(String password)
    {
    	this.password=password;
    }
    public Integer getUser_id()
    {
         return user_id;
    }
    public void setUser_id(Integer  user_id)
    {
          this.user_id=user_id;
    }


}
