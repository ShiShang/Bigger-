package top.cicle.bigger.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import top.cicle.bigger.domain.Notice;
import static top.cicle.bigger.common.biggerConstants.*;

public class NoticeDynaSqlProvider {

	
    public String addNotice(final Notice notice)
    {
    	String sql=new SQL()
    	{
    		{
    			INSERT_INTO(NOTICETABLE);
    			if(notice!=null)
    			{
    				if(notice.getTitle()!=null && !notice.getTitle().equals(""))
    				{
    					VALUES("title","#{title}");
    				}
    				if(notice.getContent()!=null && !notice.getContent().equals(""))
    				{
    					VALUES("content","#{content}");
    				}
    				if(notice.getLevel()!=null && !notice.getLevel().equals(""))
    				{
    					VALUES("level","#{level}");
    				}
    				if(notice.getUser()!=null )
    				{
    					VALUES("user_id","#{user.id}");
    				}
    			}
    			
    		}
    	}.toString();
    	
    	return sql;
    }
	
}
