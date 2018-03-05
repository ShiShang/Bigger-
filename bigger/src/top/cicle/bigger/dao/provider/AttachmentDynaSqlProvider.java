package top.cicle.bigger.dao.provider;

import static top.cicle.bigger.common.biggerConstants.*;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import top.cicle.bigger.domain.Attachment;

public class AttachmentDynaSqlProvider {

    public String selectAttachment(final Map<String,Object> params)
    {
    	String sql=new SQL()
    	{
    		{
    			SELECT("*");
    			FROM(ATTACHMENTTABLE);
    			if(params.get("title")!=null)
    			{
    				String title=(String) params.get("title");
    				WHERE(" title like CONCAT('%',#{title},'%')");
    			}
    		}
    	}.toString();
    	return sql;
    }
    
    public String addAttachment(final Attachment attachment)
    {
       String sql=new SQL()
       {
    	   {
    		   INSERT_INTO(ATTACHMENTTABLE);
    		   if(attachment!=null)
    		   {
    			   if(attachment.getTitle()!=null)
    			   {
    				   VALUES("title","#{title}");
    			   }
    			   if(attachment.getContent()!=null)
    			   {
    				   VALUES("content","#{content}");
    			   }
    			   if(attachment.getUser()!=null)
    			   {
    				   VALUES("user_id","#{user.id}");
    			   }
    			   
    		   }
    		   
    	   }
       }.toString();
       return sql;
    }
	
	
	
}
