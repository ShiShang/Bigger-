package top.cicle.bigger.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class Attachment implements Serializable{

	private Integer id;
	private String title;
	private String content;
	@DateTimeFormat(pattern="yyyy-mm-dd")   //��ʱ���ʽ�Ĵ���
	private Date create_time;
	private User user;
	private MultipartFile file;
	private Integer user_id;
	
	public Attachment()
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
         return create_time;
    }
   public void setCreatetime(Date  createTime)
    {
          this.create_time=createTime;
    }

   public User getUser()
    {
         return user;
    }
   public void setUser(User  user)
    {
          this.user=user;
    }
   public MultipartFile getFile()
   {
	   return file;
   }
   public void setFile(MultipartFile file)
   {
	   this.file=file;
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
